plugins {
    application
    // 1. Adicione o plugin do JaCoCo aqui
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(libs.guava)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "jogo.App"
}

// 2. Configure a tarefa de teste para gerar o relatório automaticamente
tasks.named<Test>("test") {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // Roda o jacoco logo após os testes
}

// 3. Configure o relatório do JaCoCo
tasks.jacocoTestReport {
    dependsOn(tasks.test) // Garante que os testes rodem antes do relatório
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco"))
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.named<Javadoc>("javadoc") {
    // Corrigido para a sintaxe do Kotlin DSL
    destinationDir = file(layout.buildDirectory.dir("docs/javadoc"))
    (options as StandardJavadocDocletOptions).memberLevel = JavadocMemberLevel.PRIVATE
}