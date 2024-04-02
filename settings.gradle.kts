rootProject.name = "pinte"
include("src:main:resources")
findProject(":src:main:resources")?.name = "resources"
