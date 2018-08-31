#include "OmokRule.h"

#include <iostream>
#include <windows.h>
using namespace std;

void OmokRule::init() {
    cursorX = cursorY = OMOK_SIZE / 2;
    turn = BlackStone;

    for(int i = 0;i < OMOK_SIZE;i++) {
        for(int j = 0;j < OMOK_SIZE;j++) {
            board[i][j] = Blank;
        }
    }

    while(!stack.empty()) {
        stack.pop();
    }
}

bool OmokRule::back() {
    pair<short, short> pos = stack.top();
    stack.pop();

    cursorX = pos.first;
    cursorY = pos.second;

    turn = (Stone)((turn + 1)%2);
    board[cursorX][cursorY] = Blank;
    return true;
}

bool OmokRule::isThereWinner() {
    Stone player = board[cursorX][cursorY];

    for(short d = 0;d < 4;d++) {
        short count = 0;
        for(short xx = cursorX + dx[d], yy = cursorY + dy[d];
            xx >= 0 && xx < OMOK_SIZE && yy >= 0 && yy < OMOK_SIZE && board[xx][yy] == player;
            xx += dx[d], yy += dy[d]) {
            count++;
        }

        for(short xx = cursorX - dx[d], yy = cursorY - dy[d];
            xx >= 0 && xx < OMOK_SIZE && yy >= 0 && yy < OMOK_SIZE && board[xx][yy] == player;
            xx -= dx[d], yy -= dy[d]) {
            count++;
        }

        if(count >= 4) {
            return true;
        }
    }

    return false;
}

bool OmokRule::isThereStone() {
    return board[cursorX][cursorY] != Blank;
}

bool OmokRule::is6Mok(Stone player) {
    for(short d = 0;d < 4;d++) {
        short count = 0;
        for(short xx = cursorX + dx[d], yy = cursorY + dy[d];
            xx >= 0 && xx < OMOK_SIZE && yy >= 0 && yy < OMOK_SIZE && board[xx][yy] == player;
            xx += dx[d], yy += dy[d]) {
            count++;
        }

        for(short xx = cursorX - dx[d], yy = cursorY - dy[d];
            xx >= 0 && xx < OMOK_SIZE && yy >= 0 && yy < OMOK_SIZE && board[xx][yy] == player;
            xx -= dx[d], yy -= dy[d]) {
            count++;
        }

        if(count >= 5) {
            return true;
        }
    }

    return false;
}

bool OmokRule::is33(Stone player) {
    Stone mine = player;

    Stone _5[][5] {
            {Blank, mine, mine, mine, Blank},
    };
    Stone _6[][6] {
            {Blank, mine, mine, Blank, mine, Blank},
            {Blank, mine, Blank, mine, mine, Blank}
    };

    board[cursorX][cursorY] = player;
    short count = 0;

    // Check the _5
    for(int i = 0;i < 1;i++) {
        for(int direction = 0;direction < 4;direction++) {
            for(int cursor = -3;cursor <= -1;cursor++) {
                bool isPass = true;

                for(int pos = 0;pos < 5;pos++) {
                    short xx = cursorX + cursor*dx[direction] + dx[direction]*pos;
                    short yy = cursorY + cursor*dy[direction] + dy[direction]*pos;

                    if(xx < 0 || xx >= OMOK_SIZE || yy < 0 || yy >= OMOK_SIZE || board[xx][yy] != _5[i][pos]) {
                        isPass = false;
                        break;
                    }
                }

                if(isPass) {
                    count++;
                }
            }
        }
    }

    for(int i = 0;i < 2;i++) {
        for(int direction = 0;direction < 4;direction++) {
            for(int cursor = -4;cursor <= -1;cursor++) {
                bool isPass = true;

                for(int pos = 0;pos < 6;pos++) {
                    short xx = cursorX + cursor*dx[direction] + dx[direction]*pos;
                    short yy = cursorY + cursor*dy[direction] + dy[direction]*pos;

                    if(xx < 0 || xx >= OMOK_SIZE || yy < 0 || yy >= OMOK_SIZE || board[xx][yy] != _6[i][pos]) {
                        isPass = false;
                        break;
                    }
                }

                if(isPass) {
                    count++;
                }
            }
        }
    }

    board[cursorX][cursorY] = Blank;
    return count >= 2;
}

bool OmokRule::is44(Stone player) {
    Stone mine = player;
    Stone other = (Stone)((player + 1)%2);

    Stone _6[][6] {
            {Blank, mine, mine, mine, mine, Blank},
            {Blank, mine, mine, mine, mine, other},
            {other, mine, mine, mine, mine, Blank}
    };

    Stone _7[][7] {
            {Blank, mine, mine, Blank, mine, mine, Blank},
            {Blank, mine, mine, Blank, mine, mine, other},
            {other, mine, mine, Blank, mine, mine, Blank},
            {Blank, mine, Blank, mine, mine, mine, Blank},
            {Blank, mine, Blank, mine, mine, mine, other},
            {other, mine, Blank, mine, mine, mine, Blank},
            {Blank, mine, mine, mine, Blank, mine, Blank},
            {Blank, mine, mine, mine, Blank, mine, other},
            {other, mine, mine, mine, Blank, mine, Blank}
    };

    board[cursorX][cursorY] = player;
    short count = 0;

    // Check the _6
    for(int i = 0;i < 3;i++) {
        for(int direction = 0;direction < 4;direction++) {
            for(int cursor = -4;cursor <= -1;cursor++) {
                bool isPass = true;

                for(int pos = 0;pos < 6;pos++) {
                    short xx = cursorX + cursor*dx[direction] + dx[direction]*pos;
                    short yy = cursorY + cursor*dy[direction] + dy[direction]*pos;

                    if(xx < 0 || xx >= OMOK_SIZE || yy < 0 || yy >= OMOK_SIZE || board[xx][yy] != _6[i][pos]) {
                        isPass = false;
                        break;
                    }
                }

                if(isPass) {
                    count++;
                }
            }
        }
    }
    // Check the _7
    for(int i = 0;i < 9;i++) {
        for(int direction = 0;direction < 4;direction++) {
            for(int cursor = -5;cursor <= -1;cursor++) {
                bool isPass = true;

                for(int pos = 0;pos < 7;pos++) {
                    short xx = cursorX + cursor*dx[direction] + dx[direction]*pos;
                    short yy = cursorY + cursor*dy[direction] + dy[direction]*pos;

                    if(xx < 0 || xx >= OMOK_SIZE || yy < 0 || yy >= OMOK_SIZE || board[xx][yy] != _7[i][pos]) {
                        isPass = false;
                        break;
                    }
                }

                if(isPass) {
                    count++;
                }
            }
        }
    }

    board[cursorX][cursorY] = Blank;
    return count >= 2;
}

Stone & OmokRule::getTurn() {
    return turn;
}

short OmokRule::getX() {
    return cursorX;
}

short OmokRule::getY() {
    return cursorY;
}

void OmokRule::move(short x, short y) {
    cursorX = x;
    cursorY = y;
}

void OmokRule::moveUp() {
    cursorY = cursorY == 0 ? OMOK_SIZE - 1 : cursorY - 1;
}

void OmokRule::moveDown() {
    cursorY = cursorY == OMOK_SIZE - 1 ? 0 : cursorY + 1;
}

void OmokRule::moveLeft() {
    cursorX = cursorX == 0 ? OMOK_SIZE - 1 : cursorX - 1;
}

void OmokRule::moveRight() {
    cursorX = cursorX == OMOK_SIZE - 1 ? 0 : cursorX + 1;
}
