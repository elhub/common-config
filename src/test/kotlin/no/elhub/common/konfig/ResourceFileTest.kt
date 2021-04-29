package no.elhub.common.konfig

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import no.elhub.common.konfig.examples.ResourceTestConfig

class ResourceFileTest : DescribeSpec({

    describe("A configuration object created from a resource file") {

        it("should parse aString correctly") {
            ResourceTestConfig.aString shouldBe "xyz"
        }

    }

})
