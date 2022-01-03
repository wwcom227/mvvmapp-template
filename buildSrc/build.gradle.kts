repositories {
    google()
    jcenter()
    mavenCentral()
    //maven { uri("$rootDir/spi_repo") }
    maven { url = uri("https://jitpack.io") }
}

allprojects {
    repositories {
//        mavenCentral()
//        google()
//        jcenter()
//        maven { url = uri("https://jitpack.io") }
    }
}

plugins {
    `kotlin-dsl`
}