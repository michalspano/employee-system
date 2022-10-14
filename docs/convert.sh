#!/bin/sh

FILE_PATH="$1"
MMDC=$(which mmdc)

OUT="output"
OUT_EXTENSION="svg"

main () {
    usage
    mmd_generate

    # open flag: --open | -o
    if [ "$2" = "--open" ] || [ "$2" = "-o" ]; then
        echo "Opening file..."; open $OUT.$OUT_EXTENSION
    fi
}

usage () {
    if [ -z "$FILE_PATH" ]; then printf "Usage: %s <file_path>\n"; exit 1; fi
}

mmd_generate () {
    FILE_NAME="${FILE_PATH%.*}"
    NEW_FILE_NAME="${FILE_NAME}.mmd"
    sed '1d;$d' "$FILE_PATH" > "temp/$NEW_FILE_NAME"

    $MMDC -i "temp/$NEW_FILE_NAME" -o $OUT.$OUT_EXTENSION
}

main "$@"