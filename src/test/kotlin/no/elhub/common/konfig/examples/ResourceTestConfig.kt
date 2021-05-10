package no.elhub.common.konfig.examples

import no.elhub.common.konfig.Configurable
import no.elhub.common.konfig.sources.ResourceFile
import java.time.Duration

object ResourceTestConfig : Configurable(arrayOf(ResourceFile("my.properties"))) {
    val aString: String = property<String>("aString")
    val aBoolean: Boolean = property<Boolean>("anInt")
    val anInt: Int = property<Int>("anInt")
    val aDuration: Duration = property<Duration>("aDuration")
}
