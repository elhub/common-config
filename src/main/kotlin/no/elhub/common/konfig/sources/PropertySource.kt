package no.elhub.common.konfig.sources

import no.elhub.common.konfig.KonfigException
import java.io.InputStream
import java.util.*

abstract class PropertySource {

    abstract fun load(properties: Properties)

    protected fun load(input: InputStream?, properties: Properties, errorMessageFn: () -> String) =
        (input ?: throw KonfigException(errorMessageFn())).use {
            properties.load(it)
        }

}
