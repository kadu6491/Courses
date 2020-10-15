#include <utility>
#include "Inventory.h"

// Allow the compiler to define the remaining
// comparison operators
using namespace std::rel_ops;

//------------------------------------------------------------------------------
Inventory::Node::Node()
    :data(Item(0, "Air"), 0)
{
    this->next = nullptr;
}

//------------------------------------------------------------------------------
Inventory::Node::Node(ItemStack s)
    :data(s)
{
    this->next = nullptr;
}

//------------------------------------------------------------------------------
Inventory::Inventory()
{
    this->head     = nullptr;
    this->tail     = nullptr;
    this->slots    = 10;
    this->occupied = 0;

    //std::cerr << Node().data << "\n";
}

//------------------------------------------------------------------------------
Inventory::Inventory(int n)
{
    this->head     = nullptr;
    this->tail     = nullptr;
    this->slots    = n;
    this->occupied = 0;
}

//------------------------------------------------------------------------------
Inventory::Inventory(const Inventory &src)
{
    // @todo implement this function
   this->head  = nullptr;
   this->tail  = nullptr;
   this->slots = src.slots;
   this->occupied = 0;
   
   for(Node* it = src.head; it != nullptr; it = it->next)
   {
   	this->addItemStackNoCheck(it->data);
   }

}

//------------------------------------------------------------------------------
Inventory::~Inventory()
{
    // @todo implement this function
	Node* it = head;
      	while(it!= nullptr)
	{
	   Node* next = it->next;
	   delete it;
          
           it = next;
	}
}

//------------------------------------------------------------------------------
bool Inventory::isFull() const
{
    // @todo implement this function
    //
    // If this is more than one line
    // in the form "return (boolean expression);"
    // you are overthinking the problem

    return (occupied == slots); // This line is a placeholder. Remove it.

}

//------------------------------------------------------------------------------
void Inventory::display(std::ostream &outs) const
{
    outs << " -Used " << occupied << " of " << slots << " slots" << "\n";

    Node* it = head;
    while(it != nullptr){
        outs << "  " << it->data << "\n";

        it = it->next;
    }
}

//------------------------------------------------------------------------------
Inventory& Inventory::operator=(Inventory rhs)
{
    std::swap(*this, rhs);
    return *this;
}

//------------------------------------------------------------------------------
void swap(Inventory& lhs, Inventory& rhs)
{
    using std::swap;

    swap(lhs.head, rhs.head);
    swap(lhs.tail, rhs.tail);
    swap(lhs.slots, rhs.slots);
    swap(lhs.occupied, rhs.occupied);
}

//------------------------------------------------------------------------------
Inventory::Node* Inventory::findMatchingItemStackNode(const ItemStack& itemStack)
{
    // @todo implement this function
    Node* it = head;
    
    while(it != nullptr)
    {
	if((it->data) == itemStack)
	{
	   return it;
	}
	it = it->next;
    }
    return nullptr;
}

//------------------------------------------------------------------------------
void Inventory::mergeStacks(ItemStack& lhs, const ItemStack& rhs)
{
    // Update lhs... remember rhs is read only
   // Node* it = head;
   // it->data = lhs;
   // it->data = rhs;
    //int num=0;
    //lhs.addItems(num);
   lhs.addItems(rhs.size());
   // rhs.size();

}


//------------------------------------------------------------------------------
void Inventory::addItemStackNoCheck(ItemStack itemStack)
{
    // @todo implement this function
   if(head == nullptr)
   {
	head = new Node(itemStack);
	tail = head;
   }

  else{
	tail->next = new Node(itemStack);
	tail = tail->next;
  }
  occupied++;
}
