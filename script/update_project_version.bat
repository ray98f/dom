@echo off

rem 进入上级目录
cd %~dp0
dir .
cd ..

rem 获取输入参数
set "command=%~1"

if "%command%"=="set" (
    if "%~2"=="" (
        echo Error: newVersion is required
        exit /b 1
    )
    set "newVersion=%~2"
    echo Setting new version to "%~2"
    mvn versions:set -DnewVersion="%~2"
) else if "%command%"=="commit" (
    echo Committing changes
    mvn versions:commit
) else if "%command%"=="revert" (
    echo Reverting changes
    mvn versions:revert
) else (
    echo Usage: %~nx0 {set newVersion^|commit^|revert}
    exit /b 1
)