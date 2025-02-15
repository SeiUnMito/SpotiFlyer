import de.comahe.i18n4k.gradle.plugin.i18n4k

/*
 *  * Copyright (c)  2021  Shabinder Singh
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  *  You should have received a copy of the GNU General Public License
 *  *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

plugins {
    id("android-setup")
    id("multiplatform-setup")
    id("multiplatform-setup-test")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
    id("de.comahe.i18n4k")
}

val statelyVersion = "1.1.7"
val statelyIsoVersion = "1.1.7-a1"

i18n4k {
    inputDirectory = "../../translations"
    packageName = "com.shabinder.common.translations"
    // sourceCodeLocales = listOf("en", "de", "es", "fr", "id", "pt", "ru", "uk")
}

kotlin {
    sourceSets {
        /*
        * Depend on https://github.com/ReactiveCircus/cache4k
        * -As Soon as Kotlin 1.5 and Compose becomes compatible
        * */
        all {
            languageSettings.apply {
                progressiveMode = true
                enableLanguageFeature("NewInference")
                useExperimentalAnnotation("kotlin.Experimental")
                useExperimentalAnnotation("kotlin.time.ExperimentalTime")
            }
        }
        commonMain {
            dependencies {
                implementation("co.touchlab:stately-concurrency:$statelyVersion")
                implementation("co.touchlab:stately-isolate:$statelyIsoVersion")
                implementation("co.touchlab:stately-iso-collections:$statelyIsoVersion")
                implementation(Extras.youtubeDownloader)
                api(Internationalization.dep)
            }
        }
    }
}
