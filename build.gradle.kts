import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.7.10"
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "cs2-editor"
version = "1.5"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.displee:disio:2.2")
    implementation("com.displee:rs-cache-library:6.8.1")
    implementation("org.fxmisc.richtext:richtextfx:0.10.9")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
}

javafx {
    modules = listOf("javafx.controls", "javafx.fxml")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    destinationDirectory.set(file(rootDir))
    manifest.attributes["Main-Class"] = "com.displee.editor.EditorKt"
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}