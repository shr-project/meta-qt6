PYPI_PACKAGE = "antlr4-python3-runtime"
inherit pypi setuptools3

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=c96f0ee5f5d08fd4387e5f00507bb13e"

SRC_URI[sha256sum] = "31f5abdc7faf16a1a6e9bf2eb31565d004359b821b09944436a34361929ae85a"

BBCLASSEXTEND = "nativesdk native"
