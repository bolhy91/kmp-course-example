import org.jetbrains.compose.desktop.application.dsl.TargetFormat

// 1
plugins {
    kotlin("multiplatform")
    alias(libs.plugins.composePlugin)
}

// 2
kotlin {
    // 1
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
// 2
    sourceSets {
        val jvmMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
                implementation(compose.desktop.currentOs)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)

                implementation(project(":shared"))
                implementation(project(":shared-ui"))
            }
        }
    }

}

compose.desktop {
    application() {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "FindTime"
            macOS {
                bundleID = "xyz.bolhy91.kmp_kodeco_example"
            }
        }
    }
}

