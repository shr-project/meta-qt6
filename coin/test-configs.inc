PREFERRED_PROVIDER_udev = "systemd"
# use gold
DISTRO_FEATURES:append = " ld-is-gold"
# use systemd
DISTRO_FEATURES:append = " systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = ""
# use wayland
DISTRO_FEATURES:append = " wayland"

INHERIT += "buildstats buildstats-summary"
INHERIT += "rm_work"

# fast compression of SDK tar package
SDK_XZ_COMPRESSION_LEVEL = "-1"

SDK_ARCHIVE_TYPE:sdkmingw32 = "zip"

# be more strict with QA warnings, turn them all to errors:
EXTRA_QA = "\
    ldflags useless-rpaths rpaths staticdev libdir xorg-driver-abi \
    textrel already-stripped incompatible-license files-invalid \
    installed-vs-shipped compile-host-path install-host-path \
    pn-overrides infodir build-deps \
    unknown-configure-option symlink-to-sysroot multilib \
    invalid-packageconfig host-user-contaminated uppercase-pn \
"
ERROR_QA:append = "${EXTRA_QA}"
WARN_QA:remove = "${EXTRA_QA}"

# enable thumb for broader test coverage (oe-core autobuilder doesn't have thumb enabled)
PREFERRED_ARM_INSTRUCTION_SET              ?= "thumb"
ARM_INSTRUCTION_SET = "${PREFERRED_ARM_INSTRUCTION_SET}"

PACKAGECONFIG:append:pn-qtbase = "\
    tslib kms gbm zstd libproxy gssapi vulkan mtdev cups \
    sql-mysql sql-odbc sql-psql sql-sqlite journald brotli \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'gtk', '', d)} \
"

# imageformats mng FIXME
PACKAGECONFIG:append:pn-qtimageformats = " jasper"

# Use latest uninative package
UNINATIVE_MAXGLIBCVERSION = "2.32"

UNINATIVE_URL ?= "http://downloads.yoctoproject.org/releases/uninative/2.9/"
UNINATIVE_CHECKSUM[aarch64] ?= "9f25a667aee225b1dd65c4aea73e01983e825b1cb9b56937932a1ee328b45f81"
UNINATIVE_CHECKSUM[i686] ?= "cae5d73245d95b07cf133b780ba3f6c8d0adca3ffc4e7e7fab999961d5e24d36"
UNINATIVE_CHECKSUM[x86_64] ?= "d07916b95c419c81541a19c8ef0ed8cbd78ae18437ff28a4c8a60ef40518e423"

# enable examples for all Qt modules
PACKAGECONFIG:append:pn-qt3d = " examples"
PACKAGECONFIG:append:pn-qt5compat = " examples"
PACKAGECONFIG:append:pn-qtbase = " examples"
PACKAGECONFIG:append:pn-qtcharts = " examples"
PACKAGECONFIG:append:pn-qtcoap = " examples"
PACKAGECONFIG:append:pn-qtconnectivity = " examples"
PACKAGECONFIG:append:pn-qtdatavis3d = " examples"
PACKAGECONFIG:append:pn-qtdeclarative = " examples"
PACKAGECONFIG:append:pn-qtimageformats = " examples"
PACKAGECONFIG:append:pn-qtmqtt = " examples"
PACKAGECONFIG:append:pn-qtmultimedia = " examples"
PACKAGECONFIG:append:pn-qtnetworkauth = " examples"
PACKAGECONFIG:append:pn-qtopcua = " examples"
PACKAGECONFIG:append:pn-qtpositioning = " examples"
PACKAGECONFIG:append:pn-qtquick3d = " examples"
PACKAGECONFIG:append:pn-qtquickdesigner-components = " examples"
PACKAGECONFIG:append:pn-qtquicktimeline = " examples"
PACKAGECONFIG:append:pn-qtremoteobjects = " examples"
PACKAGECONFIG:append:pn-qtscxml = " examples"
PACKAGECONFIG:append:pn-qtsensors = " examples"
PACKAGECONFIG:append:pn-qtserialbus = " examples"
PACKAGECONFIG:append:pn-qtserialport = " examples"
PACKAGECONFIG:append:pn-qtshadertools = " examples"
PACKAGECONFIG:append:pn-qtspeech = " examples"
PACKAGECONFIG:append:pn-qtsvg = " examples"
PACKAGECONFIG:append:pn-qttools = " examples"
PACKAGECONFIG:append:pn-qttranslations = " examples"
PACKAGECONFIG:append:pn-qtvirtualkeyboard = " examples"
PACKAGECONFIG:append:pn-qtwayland = " examples"
PACKAGECONFIG:append:pn-qtwebchannel = " examples"
PACKAGECONFIG:append:pn-qtwebsockets = " examples"
