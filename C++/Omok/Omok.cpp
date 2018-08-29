#include "Omok.h"

Omok::Omok(OmokIO *io, OmokRule *rule) {
    this->io = io;
    this->rule = rule;

    init();
    while (true) {
        Category category = goIntro();

        switch (category) {
            case Start:
                goStart();
                break;
            case Rule:
                goRule();
                break;
            case Exit:
                return;
        }
    }
}

Omok::~Omok() {
    delete io, rule;
}

void Omok::init() {
    io->init();
}

Category Omok::goIntro() {
    Category category = Start;

    io->drawIntro();
    while(true) {
        Key ch = io->read();

        switch (ch) {
            case Confirm:
                return category;
            case Up:
                category = category == Start ? Exit : (Category)(category - 1);
                io->drawDirection(category);
                break;
            case Down:
                category = category == Exit ? Start : (Category)(category + 1);
                io->drawDirection(category);
                break;
        }
    }
}

void Omok::goStart() {
    isRun = true;
    io->drawBoard();
    rule->init();
    while(isRun) {
        Key key = io->read();

        switch (key) {
            case Up:
                moveUp();
                break;
            case Down:
                moveDown();
                break;
            case Left:
                moveLeft();
                break;
            case Right:
                moveRight();
                break;
            case Confirm:
                put();
                break;
        }
    }
}

void Omok::goRule() {
    io->drawRule(rule->getExplains());
    io->read();
}

void Omok::move(short x, short y) {
    io->move(x, y);
    rule->move(x, y);
}

void Omok::moveUp() {
    io->moveUp();
    rule->moveUp();
}

void Omok::moveDown() {
    io->moveDown();
    rule->moveDown();
}

void Omok::moveLeft() {
    io->moveLeft();
    rule->moveLeft();
}

void Omok::moveRight() {
    io->moveRight();
    rule->moveRight();
}

void Omok::put() {
    const Stone stone = rule->getTurn();

    if(rule->put()) {
        switch (stone) {
            case BlackStone:
                io->drawBlackStone();
                break;
            case WhiteStone:
                io->drawWhiteStone();
                break;
        }

        if(rule->isThereWinner()) {
            isRun = false;
            io->read();
        }
    }
}
