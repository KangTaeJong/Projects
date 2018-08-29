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

constexpr pair<short, short> StartPosition = {9, 17};
constexpr pair<short, short> RulePosition = {9, 18};
constexpr pair<short, short> ExitPosition = {9, 19};

enum Color {
    Black, Blue, Green, Cyan, Red, Magenta, Brown, LightGray, DarkGray, LightBlue, LightGreen, LightCyan, LightRed, LightMagenta, Yellow, White
};

class Console final : public OmokIO {
private:
    HANDLE handle;

    void clear();
    void showCursor(bool flag);
    void setColor(Color background, Color font);
public:
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