#include "Console.h"

void Console::init() {
    OmokIO::init();
    handle = GetStdHandle(STD_OUTPUT_HANDLE);
    system("title Omok");
    system("mode con:cols=48 lines=24");
}

Key Console::read() {
    int ch = getch();

    if(ch == KEY_DIRECTION) {
        ch = getch();
    }

    switch (ch) {
        case KEY_ENTER:
        case KEY_SPACE:
            return Confirm;
        case KEY_UP:
        case KEY_W:
        case KEY_w:
            return Up;
        case KEY_DOWN:
        case KEY_S:
        case KEY_s:
            return Down;
        case KEY_LEFT:
        case KEY_A:
        case KEY_a:
            return Left;
        case KEY_RIGHT:
        case KEY_D:
        case KEY_d:
            return Right;
        case KEY_Z:
        case KEY_z:
            return Back;
        case KEY_Q:
        case KEY_q:
            return Quit;
        default:
            return (Key)ch;
    }
}

void Console::clear() {
    setColor(Black, Black);
    system("cls");
}

void Console::showCursor(bool flag) {
    CONSOLE_CURSOR_INFO info;

    info.dwSize = 1;
    info.bVisible = flag;
    SetConsoleCursorInfo(handle, &info);
}

void Console::setColor(Color background, Color font) {
    SetConsoleTextAttribute(handle, background*16 + font);
}

void Console::drawIntro() {
    clear();

    setColor(Black, LightGray);
    cout << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡á¡á¡á¡á¡á¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡á¡á¡á¡à¡à¡à¡à¡à¡à¡à¡á¡à¡à¡à¡á¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡á¡à¡à¡à¡á¡à¡à¡à¡à¡à¡à¡á¡à¡à¡à¡á¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡á¡à¡à¡à¡á¡à¡à¡à¡à¡à¡à¡á¡á¡á¡á¡á¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡á¡á¡á¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡á¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡á¡à¡à¡à¡à¡à¡à¡à¡á¡á¡á¡á¡á¡á¡á¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡á¡á¡á¡á¡á¡á¡á¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡á¡á¡á¡á¡á¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡á¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡á¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡¡¡¡¡¡¡¡¡¡Start ¡¡¡¡¡¡¡¡¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡¡¡¡¡¡¡¡¡¡Rule¡¡¡¡¡¡¡¡¡¡¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡¡¡¡¡¡¡¡¡¡Exit¡¡¡¡¡¡¡¡¡¡¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à" << "\n";
    cout << "¡¡¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à¡à" << "\n";

    showCursor(false);
    cursorX = StartPosition.first;
    cursorY = StartPosition.second;

    drawDirection(Start);
}

void Console::moveCursor(short x, short y) {
    OmokIO::moveCursor(x, y);
    SetConsoleCursorPosition(handle, {(short)(2*x + margintLeft), (short)(y + marginTop)});
}

void Console::drawDirection(Category category) {
    constexpr pair<short, short> positions[] = {
            StartPosition,
            RulePosition,
            ExitPosition
    };

    moveCursor(cursorX, cursorY);
    cout <<"¡¡";
    moveCursor(positions[category].first, positions[category].second);
    cout << "¡æ";
}

void Console::drawBoard() {
    clear();

    setColor(Brown, Black);
    for(int i = 0;i < OMOK_SIZE;i++) {
        for(int j = 0;j < OMOK_SIZE;j++) {
            moveCursor(j, i);cout << "¦«";
        }
    }

    setColor(Black, White);
    moveCursor(0, OMOK_SIZE + 2);
    cout << "Z : Back";
    moveCursor(0, OMOK_SIZE + 3);
    cout << "Q : Quit";

    showCursor(true);
    moveCursor(OMOK_SIZE/2, OMOK_SIZE/2);
}

void Console::drawRule(string explains) {
    clear();

    setColor(Black, LightGray);
    cout << explains;

    showCursor(false);
    read();
}

void Console::drawBlackStone() {
    setColor(Brown, Black);
    cout << "¡Ü";
    moveCursor(cursorX, cursorY);
}

void Console::drawWhiteStone() {
    setColor(Brown, White);
    cout << "¡Ü";
    moveCursor(cursorX, cursorY);
}

void Console::drawBoardLine() {
    setColor(Brown, Black);
    cout << "¦«";
    moveCursor(cursorX, cursorY);
}

void Console::message(string msg) {
    MessageBox(NULL, msg.c_str(), "Omok", MB_OK);
}


