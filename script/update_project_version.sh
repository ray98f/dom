#!/bin/bash

# 获取脚本所在目录的上级目录
parent_dir=$(dirname "$0")

cd $parent_dir
cd ..

# 获取输入参数
case $1 in
    set)
        if [ -z "$2" ]; then
            echo "Error: newVersion is required"
            exit 1
        fi
        newVersion=$2
        echo "Setting new version to ${newVersion}"
        mvn versions:set -DnewVersion=${newVersion}
        ;;
    commit)
        echo "Committing changes"
        mvn versions:commit
        ;;
    revert)
        echo "Reverting changes"
        mvn versions:revert
        ;;
    *)
        echo "Usage: $0 {set newVersion|commit|revert}"
        exit 1
        ;;
esac
