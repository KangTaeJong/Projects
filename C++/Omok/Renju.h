#ifndef OMOK_RENJU_H
#define OMOK_RENJU_H

#include "OmokRule.h"

class Renju final : public OmokRule {
public:
    void init() override;

    string getExplains() override;

    bool put() override;
};


#endif
