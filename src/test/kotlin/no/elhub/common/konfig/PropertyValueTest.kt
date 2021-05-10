package no.elhub.common.konfig

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import no.elhub.common.konfig.examples.ResourceTestConfig
import java.time.Duration

class PropertyValueTest : DescribeSpec({

    describe("The my.properties configuration") {

        it("should contain aString with value xyz") {
            ResourceTestConfig.aString shouldBe "xyz"
        }

        it("should contain aBoolean with value false") {
            ResourceTestConfig.aBoolean shouldBe false
        }

        it("should contain anInt with value 123") {
            ResourceTestConfig.anInt shouldBe 123
        }

        it("should contain aDuration with value 30s") {
            ResourceTestConfig.aDuration shouldBe Duration.ofSeconds(30)
        }

    }

})
