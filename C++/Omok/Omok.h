#ifndef OMOK_OMOK_H
#define OMOK_OMOK_H

#include "OmokIO.h"
#include "OmokRule.h"

class Omok {
private:
    bool isRun;
    OmokIO *io;
    OmokRule *rule;

public:
    Omok(OmokIO *io, OmokRule *rule);
    ~Omok();

    void init();
    Category goIntro();
    void goStart();
    void goRule();
    void move(short x, short y);
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
    void put();
};


#endif