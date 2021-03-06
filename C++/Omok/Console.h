#ifndef OMOK_CONSOLE_H
#define OMOK_CONSOLE_H

#include "OmokIO.h"
#include <conio.h>
#include <windows.h>
#include <iostream>
using namespace std;

constexpr int KEY_DIRECTION = 224;
constexpr int KEY_UP = 72;
constexpr int KEY_DOWN = 80;
constexpr int KEY_LEFT = 75;
constexpr int KEY_RIGHT = 77;
constexpr int KEY_W = 'W';
constexpr int KEY_w = 'w';
constexpr int KEY_A = 'A';
constexpr int KEY_a = 'a';
constexpr int KEY_S = 'S';
constexpr int KEY_s = 's';
constexpr int KEY_D = 'D';
constexpr int KEY_d = 'd';
constexpr int KEY_SPACE = 32;
constexpr int KEY_ENTER = 13;
constexpr int KEY_Z = 'Z';
constexpr int KEY_z = 'z';
constexpr int KEY_Q = 'Q';
constexpr int KEY_q = 'q';


constexpr pair<short, short> StartPosition = {5, 16};
constexpr pair<short, short> RulePosition = {5, 17};
constexpr pair<short, short> ExitPosition = {5, 18};

enum Color {
    Black, Blue, Green, Cyan, Red, Magenta, Brown, LightGray, DarkGray, LightBlue, LightGreen, LightCyan, LightRed, LightMagenta, Yellow, White
};

class Console final : public OmokIO {
private:
    short marginTop = 1, margintLeft = 8;
    HANDLE handle;

    void clear();
    void showCursor(bool flag);
    void setColor(Color background, Color font);
public:
    void message(string msg) override;

    Key read() override;

    void moveCursor(short x, short y) override;

    void init() override;

    void drawIntro() override;

    void drawDirection(Category category) override;

    void drawBoard() override;

    void drawRule(string explains) override;

    void drawBlackStone() override;

    void drawWhiteStone() override;

    void drawBoardLine() override;
};

#endif