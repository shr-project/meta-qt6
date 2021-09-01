inherit pypi setuptools3

python() {
    if 'meta-python' not in d.getVar('BBFILE_COLLECTIONS').split():
        raise bb.parse.SkipRecipe('Requires meta-python to be present.')
}

DEPS += " python3-jinja2 \
          python3-click \
          python3-pyyaml \
          python3-pytest \
          python3-six \
          python3-path \
          python3-antrl4-runtime \
          python3-watchdog \
          python3-markupsafe \
          python3-setuptools \
        "
DEPENDS += "${DEPS}"
RDEPENDS:${PN} += "${DEPS}"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eee61e10a40b0e3045ee5965bcd9a8b5"
SRC_URI = "git://code.qt.io/qt/qtinterfaceframework-qface.git;protocol=https;nobranch=1"
SRCREV = "6b7d4f9560f4ca6027ada9f0df25bf0262c71294"
PV = "2.0.4"
S = "${WORKDIR}/git"
CLEANBROKEN = "1"

BBCLASSEXTEND = "nativesdk native"
