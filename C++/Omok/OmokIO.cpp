#include "OmokIO.h"

void OmokIO::init() {
    cursorX = cursorY = 0;
}

void OmokIO::moveCursor(short x, short y) {
    this->cursorX = x;
    this->cursorY = y;
}

void OmokIO::move(short x, short y) {
    moveCursor(x, y);
}

void OmokIO::moveUp() {
    cursorY = cursorY == 0 ? OMOK_SIZE - 1 : cursorY - 1;
    moveCursor(cursorX, cursorY);
}

void OmokIO::moveDown() {
    cursorY = cursorY == OMOK_SIZE - 1 ? 0 : cursorY + 1;
    moveCursor(cursorX, cursorY);
}

void OmokIO::moveLeft() {
    cursorX = cursorX == 0 ? OMOK_SIZE - 1 : cursorX - 1;
    moveCursor(cursorX, cursorY);
}

void OmokIO::moveRight() {
    cursorX = cursorX == OMOK_SIZE - 1 ? 0 : cursorX + 1;
    moveCursor(cursorX, cursorY);
}
