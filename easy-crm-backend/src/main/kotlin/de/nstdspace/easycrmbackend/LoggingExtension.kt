import io.github.oshai.kotlinlogging.KotlinLogging

val Any.logger get() = KotlinLogging.logger(this.javaClass.name)
