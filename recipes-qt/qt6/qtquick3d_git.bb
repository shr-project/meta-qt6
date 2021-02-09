LICENSE = "GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtquick3d-assimp.git;name=assimp;branch=upstream/master;protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/assimp/src \
    file://0001-CMake-allow-tools-build-without-opengl.patch \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native qtquick3d-native"

BBCLASSEXTEND =+ "native nativesdk"

PACKAGECONFIG ??= ""
PACKAGECONFIG[system-assimp] = "-DFEATURE_system-assimp=ON,-DFEATURE_system-assimp=OFF,assimp"

FILES_${PN}-qmlplugins += " \
  ${QT6_INSTALL_QMLDIR}/QtQuick3D/Helpers/meshes/*.mesh \
"

SRCREV_FORMAT = "qtquick3d_assimp"
SRCREV_qtquick3d = "4a1d7bf5b6cf95a5cf669c95ab261f46d2e09e97"
SRCREV_assimp = "2cb0304e71166a5ebba61101ea13e6d97304c7e5"
