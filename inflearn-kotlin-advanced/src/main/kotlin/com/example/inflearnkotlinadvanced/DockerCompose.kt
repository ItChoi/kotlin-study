package com.example.inflearnkotlinadvanced

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    val yml = dockerCompose {
        this.version { 3 } // this 생략 가능
        //this.version { 4 } // 중복 방지 -> onceNotNull
        service(name = "db") {
            image { "mysql" }
            env("USER" - "myuser") // 연산자 오버로딩 활용해 객체 생성
            env("PASSWORD" - "mypassword") // 연산자 오버로딩 활용해 객체 생성
            port(host = 9999, container = 3306)
        }
    }

    // @DslMarker 활용
    val yml2 = dockerCompose {
        service("") { // this -> dockerCompose
            // service 안 service -> 계층적으로 이상하다.
            //service("") { } // this -> dockerCompose, @DslMarker를 활용해 여기서 this는 dockerCompose를 가르킬 수 없게 되어 컴파일 에러 발생
            this@dockerCompose.service("") { } // this@dockerCompose로 쓰면 사용 가능하긴하다.

        }
    }

    println(yml.render("  "))
}

fun dockerCompose(init: DockerCompose.() -> Unit): DockerCompose {
    val dockerCompose = DockerCompose()
    init(dockerCompose)
    //dockerCompose.init()
    return dockerCompose
}

@YamlDsl
class DockerCompose {
    //private var version: Int? = null // 버전은 null이 들어가지 않아서 기본 값을 null로 하는건 아쉽다.
    //private var version: Int by Delegates.notNull() // 위임 프로퍼티 사용
    private var version: Int by onceNotNull()
    private val services = mutableListOf<Service>()

    fun version(init: () -> Int) {
        //version = init.invoke()
        version = init()
    }

    fun render(indent: String): String {
        /*return StringBuilder().apply {
            appendNew("version: '$version'")
        }.toString()*/
        val builder = StringBuilder()
        builder.appendNew("version: $version")
        builder.appendNew("services: $version")
        builder.appendNew(services.joinToString("\n") { it.render(indent) }.addIndent(indent, 1))
        return builder.toString()
    }

    fun service(name: String, init: Service.() -> Unit) {
        val service = Service(name)
        service.init()
        services.add(service)
    }
}

@YamlDsl
class Service(private val name: String) {
    private var image: String by onceNotNull()
    private val environments = mutableListOf<Environment>()
    private val portRules = mutableListOf<PortRule>()

    fun image(init: () -> String) {
        this.image = init()
    }

    fun env(environment: Environment) {
        this.environments.add(environment)
    }

    fun port(host: Int, container: Int) {
        this.portRules.add(PortRule(host = host, container = container))
    }


    fun render(indent: String): String {
        val builder = StringBuilder()
        builder.appendNew("$name")
        builder.appendNew("image: $image", indent, 1)
        builder.appendNew("environment:")
        //builder.appendNew(environments.joinToString("\n") { "- ${it.key}: ${it.value}" }.addIndent(indent, 2))
        environments.joinToString("\n") { "- ${it.key}: ${it.value}" }
            .addIndent(indent, 1)
            .also { builder.appendNew(it) }
        builder.appendNew("port:")
        portRules.joinToString("\n") { "- \"${it.host}:${it.container}\"" }
            .addIndent(indent, 1)
            .also { builder.appendNew(it) }

        return builder.toString()

    }
}

data class Environment(
    val key: String,
    val value: String,
)

operator fun String.minus(other: String): Environment {
    return Environment(
        key = this,
        value = other
    )
}

data class PortRule(
    val host: Int,
    val container: Int,
)

// 동일 프로퍼티 중복 방지
fun <T> onceNotNull() = object : ReadWriteProperty<Any?, T> {
    private var value: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (this.value == null) {
            throw IllegalArgumentException("변수가 초기화되지 않았습니다")
        }
        return this.value!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (this.value != null) {
            throw IllegalArgumentException("이 변수는 한 번만 값을 초기화할 수 있습니다.")
        }
        this.value = value
    }
}

// render를 도울 util 함수
fun StringBuilder.appendNew(str: String, indent: String = "", times: Int = 0) {
    /**
     * yml 특징 -> times: indent를 몇 번 반복할지
     * a:
     *   b:
     *     c: 123
     */
    (1..times).forEach { _ ->this.append(indent) } // 필요하지 않은 파라미터는 언더라인(-)으로 표기한다.
    this.append(str)
    this.append("\n")
}

fun String.addIndent(indent: String, times: Int = 0): String {
    //여러줄 표현 문자열을 한 번에 들여쓰기하는 용도
    val allIndent = (1..times).joinToString("") { indent } // [1, 2, 3] indent = "  " -> ["  ", "  ", "  "] -> "      "
    return this.split("\n")
        .joinToString("\n") { "$allIndent$it" }
}

@DslMarker
annotation class YamlDsl
