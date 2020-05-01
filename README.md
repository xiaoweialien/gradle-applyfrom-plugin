# gradle-applyfrom-plugin
Due to the limitation of gradle apply from limitation, apply from https endpoint can not using authentication. 
So using this plugin can apply from a gradle script file from dependencies.

## Install
All Gradle versions:
```groovy
buildscript {
	repositories {
		maven {
			url "https://plugins.gradle.org/m2/"
		}
	}
	dependencies {
		classpath "gradle.plugin.com.sharp.gradle:gradle-applyfrom-plugin:1.0.1"
	}
}

apply plugin: "com.sharp.applyfrom"
```

Gradle 2.1 or newer:
```groovy
plugins {
	id "com.sharp.applyfrom" version "1.0.1"
}
```

# Usage
Option 1:
```groovy
dependencies {
	scripts group: '{{groupA}}', name: '{{nameA}}', version: '{{versionB}}', ext: 'gradle'
}
```
```groovy
applyfrom '{{groupA}}:{{nameA}}:{{versionB}}'
```

# Changes

### 1.0
* initial