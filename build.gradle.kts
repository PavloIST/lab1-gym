plugins {
    id("java")


}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {

    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}
tasks.test {
    useJUnitPlatform()
}