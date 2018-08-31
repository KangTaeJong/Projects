#include "Renju.h"

void Renju::init() {
    OmokRule::init();
}

bool Renju::put() {
    if(isThereStone()) {
        return false;
    }

    if(turn == BlackStone && is6Mok(BlackStone)) {
        return false;
    }

    if(turn == BlackStone && is33(BlackStone)) {
        return false;
    }

    if(turn == BlackStone && is44(BlackStone)) {
        return false;
    }

    board[cursorX][cursorY] = turn;
    turn = (Stone)((turn + 1)%2);
    stack.push(make_pair(cursorX, cursorY));
    return true;
}

string Renju::getExplains() {
    return "Hi";
}

bool Renju::back() {
    return OmokRule::back();
}
