#ifndef OMOK_RULE_H
#define OMOK_RULE_H

#include "Intent.h"
#include <stack>
#include <string>
using namespace std;

enum Stone {
    BlackStone, WhiteStone, Blank
};

class OmokRule {
protected:
    short dx[8] = {0, 1, 1, 1, 0, -1, -1, -1};
    short dy[8] = {-1, -1, 0, 1, 1, 1, 0, -1};

    short cursorX, cursorY;
    Stone turn;
    Stone board[OMOK_SIZE][OMOK_SIZE];
    stack<pair<int, int>> stack;

    bool isThereStone();
    bool is6Mok(Stone player);
    bool is33(Stone player);
    bool is44(Stone player);
public:
    virtual void init();
    virtual string getExplains() = 0;
    virtual bool put() = 0;
    virtual bool back() = 0;
    bool isThereWinner();

    Stone& getTurn();

    short getX();
    short getY();
    void move(short x, short y);
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
};

#endif
