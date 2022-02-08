#!/bin/bash
BuildVer="Test"
Publish="Publish"
folder_build="build"
folder_icon="AppIcon.iconset"
folder_file_icon="$folder_icon/icon_128x128.png"
icon_file="Icon.icns"

#Icon路径
file_to_icon="back.png"

#打包的资源
source="source"

#打包名字
dmgname="BiliSP"

#build文件夹
if [ ! -d "$folder_build" ];
then
    mkdir "$folder_build"
    echo "创建$folder_build 成功"
fi

#C Compile
echo "编译C动态支持库"
# shellcheck disable=SC2034
CFile="test"
# shellcheck disable=SC2066
for file in "$CFile"
do
  gcc -dynamiclib "./CLib/$file.c" -o "./CLib/$file.dylib"
  cp ./Clib/*.dylib ./runLib/
done

#Java Compile
echo "编译JAVA源码"
mvn clean compile assembly:single
cp ./target/*.jar ./runLib/

# shellcheck disable=SC2164
cd "$folder_build"

#图标文件夹
if [ ! -d "$folder_icon" ];
then
    mkdir "$folder_icon"
    echo "创建$folder_icon 成功"
fi

#检查图标
echo "生产图标"
file_to_icon="../$file_to_icon"
if [ ! -f "$file_to_icon" ];
then
    echo "未找到图标文件"
    exit 1
fi

#创建图标
if [ ! -f "$folder_file_icon" ];
then
    sips -z 128 128 "$file_to_icon" --out "$folder_file_icon"
    echo "创建$folder_file_icon 成功"
fi
if [ ! -f "$icon_file" ];
then
    iconutil -c icns "$folder_icon" -o Icon.icns
    echo "创建$folder_icon 成功"
fi

#建立结构
if [ ! -d "$source" ];
then 
    mkdir "$source"
    mkdir "$source/lib/"
    mkdir "$source/pic/"
    echo "创建$source 成功"
fi

echo "移动支持库"
if [ "$BuildVer" = "Test" ];
then
  cp ../runLib/*.properties "$source/lib/"
fi
cp ../runLib/*.dylib "$source/lib/"
cp ../runLib/*.png "$source/pic/"
cp ../runLib/*.jpg "$source/pic/"
cp ../runLib/*.jar "$source/main.jar"

echo "开始打包..."
jpackage -i "$source" -n "$dmgname" --type "dmg" --icon "Icon.icns" --main-jar main.jar
echo "打包完成"

cp ./*.dmg ../
echo "开始清理"
cd ../
rm -R "$folder_build"
echo "清理完成"
open .
