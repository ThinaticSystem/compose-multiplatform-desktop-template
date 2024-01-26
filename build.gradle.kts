import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.graalvm.buildtools.native")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Dependency Injection
    //// Koin
    val koinVersion: String by project
    implementation("io.insert-koin:koin-core:$koinVersion")
    testImplementation("io.insert-koin:koin-test:$koinVersion")
    testImplementation("io.insert-koin:koin-test-junit4:$koinVersion")

    // Framework
    //// Compose Desktop
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)

    // UI
    //// Window Manager
    val jwmVersion: String by project
    implementation("io.github.humbleui:jwm:$jwmVersion")

    //// Material
    val composeVersion: String by project
    implementation("org.jetbrains.compose.material3:material3-desktop:$composeVersion")

    // Navigation
    //// Voyager
    val voyagerVersion: String by project
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-koin:$voyagerVersion")

    // Serialization
    //// kotlinx.serialization
    val kotlinxSerializationVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

    // Testing
    testImplementation(compose.desktop.uiTestJUnit4)
}

val entryPointClassName = "MainKt"

graalvmNative {
    toolchainDetection.set(false)
    binaries {
        named("main") {
            mainClass.set(entryPointClassName)
            buildArgs("-Djava.awt.headless=false")
        }
    }

    agent {
        defaultMode.set("standard")

        metadataCopy {
            inputTaskNames.add("run") // Tasks previously executed with the agent attached.
            outputDirectories.add("src/main/resources/META-INF/native-image")
            mergeWithExisting.set(true)
        }
    }
}

compose.desktop {
    application {
        mainClass = entryPointClassName

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinJvmComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
    }
}
