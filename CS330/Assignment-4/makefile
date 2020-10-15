MAINPROG=storage
CPPS= storage.cpp Item.cpp ItemFactory.cpp ItemStack.cpp Inventory.cpp Armour.cpp Consumable.cpp utilities.cpp
TEST_CPPS= Item.cpp ItemStack.cpp Inventory.cpp TestNewClasses.cpp Armour.cpp Consumable.cpp utilities.cpp
DIR=${PWD}
ASST=$(notdir ${DIR})
ifneq (,$(findstring MinGW,$(PATH)))
DISTR=MinGW
EXE=.exe
LFLAGS=
else
DISTR=Unix
EXE=
LFLAGS= -Wall -fsanitize=address,leak -fuse-ld=gold
endif
#
########################################################################
# Macro definitions for "standard" C and C++ compilations
#
CPPFLAGS=-g -std=c++14 -D$(DISTR)
CFLAGS=-g
TARGET=$(MAINPROG)$(EXE)
LINK=g++ $(CPPFLAGS)
#
CC=gcc
CPP=g++
#
#
#  In most cases, you should not change anything below this line.
#
#  The following is "boilerplate" to set up the standard compilation
#  commands:
#


OBJS=$(CPPS:%.cpp=%.o)
DEPENDENCIES = $(CPPS:%.cpp=%.d)

TEST_OBJS=$(TEST_CPPS:%.cpp=%.o)


%.d: %.cpp
	touch $@

%.o: %.cpp
	$(CPP) $(CPPFLAGS) -MMD -o $@ -c $*.cpp

#
# Targets:
#
all: $(TARGET) tests


win: $(OBJS)
	$(LINK) $(FLAGS) -o $(TARGET) $(OBJS)

$(TARGET): $(OBJS)
	$(LINK) $(FLAGS) -o $(TARGET) $(OBJS) $(LFLAGS)

tests: $(TEST_OBJS)
	$(LINK) $(FLAGS) -o testNewClasses $(TEST_OBJS)


clean:
	-/bin/rm -f *.d *.o $(TARGET) testNewClasses



make.dep: $(DEPENDENCIES)
	-cat $(DEPENDENCIES) > $@

include make.dep
