@echo off
if exist projman.jar (
    java -jar projman.jar %*
) else (
    echo Project not Built
    echo Building Project
    call build.bat
    call projman.bat %*
)
@echo on