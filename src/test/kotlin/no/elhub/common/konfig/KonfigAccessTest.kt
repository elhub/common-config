package no.elhub.common.konfig

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import no.elhub.common.konfig.examples.ResourceTestConfig

class KonfigAccessTest : DescribeSpec({

    describe("Any configuration object") {

        it("should be able to expose the value through a val") {
            ResourceTestConfig.aString shouldBe "xyz"
        }

        it("should be able to expose the value through get") {
            ResourceTestConfig.get<String>("aString") shouldBe "xyz"
        }

        it("should be possible to test for the presence of the variable") {
            ResourceTestConfig.contains("aString") shouldBe true
        }

        it("should not find undefined variabled") {
            ResourceTestConfig.contains("notAPresentString") shouldBe false
        }


    }

})
