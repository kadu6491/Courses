MAINPROG=storage
CPPS= storage.cpp Item.cpp ItemStack.cpp Inventory.cpp utilities.cpp
INVENTORY_TEST_CPPS= Item.cpp ItemStack.cpp Inventory.cpp TestInventory.cpp utilities.cpp
ITEMSTACK_TEST_CPPS= Item.cpp ItemStack.cpp TestItemStack.cpp utilities.cpp
DIR=${PWD}
ASST=$(notdir ${DIR})
ifneq (,$(findstring MinGW,$(PATH)))
DISTR=MinGW
EXE=.exe
LFLAGS=
else
DISTR=Unix
EXE=
LFLAGS=-fsanitize=leak,address -fuse-ld=gold
endif
#
########################################################################
# Macro definitions for "standard" C and C++ compilations
#
CPPFLAGS=-g -std=c++14 -D$(DISTR) -Wall -Wextra -Wpedantic
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

INVENTORY_TEST_OBJS=$(INVENTORY_TEST_CPPS:%.cpp=%.o)
ITEMSTACK_TEST_OBJS=$(ITEMSTACK_TEST_CPPS:%.cpp=%.o)

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

tests: $(INVENTORY_TEST_OBJS) $(ITEMSTACK_TEST_OBJS)
	$(LINK) $(FLAGS) -o testInventory$(EXE) $(INVENTORY_TEST_OBJS)
	$(LINK) $(FLAGS) -o testItemStack$(EXE) $(ITEMSTACK_TEST_OBJS)


clean:
	-/bin/rm -f *.d *.o $(TARGET) testInventory$(EXE) testItemStack$(EXE)



make.dep: $(DEPENDENCIES)
	-cat $(DEPENDENCIES) > $@

include make.dep
