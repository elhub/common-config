package no.elhub.common.konfig.examples

import no.elhub.common.konfig.Configurable
import no.elhub.common.konfig.sources.ResourceFile

object ResourceTestConfig : Configurable(arrayOf(ResourceFile("my.properties"))) {
    val aString: String = property<String>("aString")
}
