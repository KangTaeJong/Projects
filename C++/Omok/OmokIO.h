#ifndef OMOK_IO_H
#define OMOK_IO_H

#include "Intent.h"
#include <string>
using namespace std;

class OmokIO {
protected:
    short cursorX, cursorY;
public:
    virtual Key read() = 0;
    virtual void moveCursor(short x, short y) = 0;
    virtual void init();
    virtual void drawIntro() = 0;
    virtual void drawDirection(Category category) = 0;
    virtual void drawBoard() = 0;
    virtual void drawRule(string explains) = 0;
    virtual void drawBlackStone() = 0;
    virtual void drawWhiteStone() = 0;
    virtual void drawBoardLine() = 0;
    void move(short x, short y);
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
};


#endif
