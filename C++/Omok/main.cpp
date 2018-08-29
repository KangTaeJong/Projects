#include "Console.h"
#include "Renju.h"
#include "Omok.h"

int main() {
    new Omok(new Console(), new Renju());
}