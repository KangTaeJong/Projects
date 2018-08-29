cmake_minimum_required(VERSION 3.12)
project(Omok)

set(CMAKE_CXX_STANDARD 17)

add_executable(Omok main.cpp Omok.cpp Omok.h OmokIO.cpp OmokIO.h OmokRule.cpp OmokRule.h Console.cpp Console.h Renju.cpp Renju.h Intent.cpp Intent.h)