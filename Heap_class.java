class HeapElement implements Heapable {
    Integer val;
    public HeapElement(Integer val) {
        this.val = val;
    }
    public Integer value()  {
        return this.val;
    }
}
class Heap<E extends Heapable>  {
    Integer hSize;
    ArrayList<E> ip;
    public Heap(ArrayList<E> ip)   {
        this.ip = ip;
        this.hSize = this.ip.size();
        this.buildHeap();
    }
    public Heap()   {
        this.ip = new ArrayList<>();
        this.hSize = 0;
        
    }
    public void buildHeap() {
        this.hSize = this.ip.size();
        int i = this.hSize-1-((this.hSize +1)/2);
        while(true) {
            if(i < 0)   break;
            maxHeapify(i);
            i--;
        }
    }
    public void maxHeapify(int ind)    {
        this.hSize = this.ip.size();
        int maxInd = this.hSize-1-((this.hSize +1)/2);
        if(ind > maxInd || ind < 0)  {
            return;
        }
        E lChild = this.ip.get(2*ind+1);
        E mChild = lChild;
        E curr = this.ip.get(ind);
        int mChildInd = 2*ind+1;
        if(2*ind+2 < this.hSize)   {
            E rChild = this.ip.get(2*ind+2);
            mChild = (lChild.value() > rChild.value())? lChild: rChild;
            mChildInd = (lChild.value() > rChild.value())? 2*ind+1 : 2*ind+2;
        }
        if(mChild.value() > curr.value())   {
            this.ip.set(ind, mChild);
            this.ip.set(mChildInd, curr);
            this.maxHeapify(mChildInd);
        }
    }
    public E remove()   {
        E max = this.ip.get(0);
        if(this.hSize == 1) {
            this.hSize = 0;
            return this.ip.remove(0);
        }
        this.ip.set(0, this.ip.remove(this.ip.size()-1));
        this.hSize--;
        maxHeapify(0);
        return max;
    }
    public E peek() {
        return this.ip.get(0);
    }
    public void add(E element)  {
        this.ip.add(element);
        int parentInd = (this.hSize-1)/2;
        this.hSize += 1;
        if(this.hSize == 1) return;
        this.maxHeapifyUp(parentInd);
    }
    public void maxHeapifyUp(int ind)   {
        E curr = this.ip.get(ind);
        E lChild = this.ip.get(2*ind+1);
        E mChild = lChild;
        int mChildInd = 2*ind+1;
        if(2*ind+2 < this.hSize)    {
            E rChild = this.ip.get(2*ind+2);
            mChild = (lChild.value() > rChild.value())? lChild: rChild;
            mChildInd = (lChild.value() > rChild.value())? 2*ind+1 : 2*ind+2;
        }
        if(mChild.value() > curr.value())   {
            this.ip.set(ind, mChild);
            this.ip.set(mChildInd, curr);
            if(ind == 0)    return;
            this.maxHeapifyUp((ind-1)/2);
        }
    }
}
interface Heapable  {
    public Integer value();
}
