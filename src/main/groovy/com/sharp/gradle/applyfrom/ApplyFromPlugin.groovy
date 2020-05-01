package com.sharp.gradle.applyfrom

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration

class ApplyFromPlugin implements Plugin<Project> {

    private static final String CONFIGURATION_SCRIPTS = 'scripts'

    @Override
    void apply(Project project) {
        Set<String> scriptSet = new HashSet<>()

        Configuration scriptsDependencies = project.configurations.create(CONFIGURATION_SCRIPTS)
        scriptsDependencies.transitive = false

        project.extensions.applyfrom = { String scriptId ->
            if (scriptId.contains(':')) {
                def scriptIds = scriptId.split(':')
                def script = scriptIds[1] + "-" + scriptIds[2] +".gradle"
                scriptSet.add(getScriptPath(project, script))
            } else {
                scriptSet.add("${project.buildDir}/scripts/${script}")
            }
        }

        project.afterEvaluate {
            scriptsDependencies.resolve().each { scriptFile ->
                def scriptFolder = project.file(getScriptFolderPath(project))
                project.copy {
                    from scriptFile
                    into scriptFolder
                    includeEmptyDirs = false
                }
            }

            scriptSet.each {
                project.apply ( from: it )
            }
        }
    }

    static String getScriptPath(Project project, String script) {
        "${project.buildDir}/scripts/${script}"
    }

    static getScriptFolderPath (Project project) {
        "${project.buildDir}/scripts/"
    }
}
