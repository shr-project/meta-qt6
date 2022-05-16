LICENSE = "BSD-3-Clause & (GFDL-1.3 & BSD-3-Clause & GPL-3.0-only | The-Qt-Company-Commercial)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://src/3rdparty/PhysX/LICENSE.md;md5=bf77e804d5e92c7e2764e9faf9ec1933 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE:aarch64 = "(.*)"
COMPATIBLE_MACHINE:arm = "(.*)"
COMPATIBLE_MACHINE:x86 = "(.*)"
COMPATIBLE_MACHINE:x86-64 = "(.*)"

DEPENDS = "qtbase qtquick3d qtdeclarative-native"
