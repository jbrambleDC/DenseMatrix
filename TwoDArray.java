public class TwoDArray {
  
  int[] data;  //a 1D array of length rows x columns. Holds elements of 2D array in row-major order
  int numberOfElements; //the number of data stored in this multiarray, i.e., the value of 'm'
  int numberOfRows;  //the number of dimensions of this multiarray, i.e., the value of 'n'
  int numberOfColumns;
  
  public TwoDArray (int numberOfRows, int numberOfColumns) { //constructor for 2DArray
	  if(numberOfRows > 0 && numberOfColumns > 0){
		  this.numberOfRows = numberOfRows;
		  this.numberOfColumns = numberOfColumns;
		  this.numberOfElements = numberOfRows*numberOfColumns;
		  this.data = new int[this.numberOfElements];
		  for(int i = 0; i < this.numberOfRows; i++){
			  for(int j = 0; j < this.numberOfColumns; j++){
				  this.data[i*this.numberOfColumns+j]=0;
			  }
		  }
	  }
	  else{
		  throw new IllegalArgumentException();
	  }
  }
  
  public TwoDArray(int numberOfRows,int numberOfColumns,int y[][] ){ //constructor that sets values in Array
	  if(numberOfRows > 0 && numberOfColumns > 0){
		  this.numberOfRows = numberOfRows;
		  this.numberOfColumns = numberOfColumns;
		  this.numberOfElements = numberOfRows*numberOfColumns;
		  this.data = new int[this.numberOfElements];
		  
		  for(int i = 0; i < numberOfRows; i++){
			  for(int j = 0; j < numberOfColumns; j++){
				  this.data[i*numberOfColumns+j]=y[i][j];
			  }
		  }
	  }
	  else{
		  throw new IllegalArgumentException();
	  }
    
  }
  
  public int getNumberOfRows () {
	  return this.numberOfRows;
  }
  
  public int getNumberOfColumns () {
	  return this.numberOfColumns;
  }
  
  public int getNumberOfElements (){
	  return this.numberOfElements;
  }
  
  private int getIndex (int row, int column) { // returns the index of the the value at (m,n) in the matrix
	  if(row >= 0 && row  <= this.numberOfRows - 1  && column >= 0 && column <= this.numberOfColumns - 1){
		  return row*this.numberOfColumns + column;
	  }
	  else{
		  throw new IllegalArgumentException();
	  }
  }
  
  public int getElement (int row, int column) { // returns the element at (m,n) in matrix. where m and n correspond to row and column
	  if(row >= 0 && row  <= this.numberOfRows - 1  && column >= 0 && column <= this.numberOfColumns - 1){
		  return this.data[this.getIndex(row,column)];
	  }
	  else{
		  throw new IllegalArgumentException();
	  }
  }
  
  public void setElement (int row, int column, int value) { //sets the element at (m,n) to value
	  if(row >= 0 && row  <= this.numberOfRows - 1  && column >= 0 && column <= this.numberOfColumns - 1){
		  this.data[this.getIndex(row, column)] = value;
	  }
	  else{
		  throw new IllegalArgumentException();
	  }
  }
  
  private int[] getIndices (int index) { // returns row and column of value at corresponding index
	  if(index >= 0){
		  int[] indices = new int[2];
		  indices[1] = index%this.numberOfColumns; 
		  indices[0] = index/this.numberOfColumns;
		  return indices;
	  }
	  else{
		  throw new IllegalArgumentException();
	  }
  }
  
  public int sum() { // returns sum of values in array
	  int totalSum = 0;
	  for(int i=0; i<this.numberOfElements; i++)
		  totalSum+=this.data[i];
	  return totalSum;
  }
  
  public TwoDArray transpose() { //transposes matrix
	  TwoDArray transposed = new TwoDArray(this.numberOfColumns,this.numberOfRows);
	  for(int i = 0; i < this.numberOfRows; i++){
		  for(int j = 0; j < this.numberOfColumns; j++){
			  transposed.setElement(j, i, this.data[i*this.numberOfColumns+j]);
		  }
	  }
	  return transposed;
	  
  }
  

  
  public String toString() { //returns a string representation of the matrix
	  String s ="\n";
	  for(int i = 0; i < this.numberOfRows; i++){
		  for(int j = 0; j < this.numberOfColumns; j++){
			  s+= this.data[i*this.numberOfColumns+j] + " ";
			  
		  }
		  s+="\n";
	  }
	  return s;
  } 
  
  public static void main(String args[]) {
    
   TwoDArray D = new TwoDArray (3,2);  
    D.setElement (0,0, 1); 
    D.setElement (0,1, 2); 
    D.setElement (1,0, 3); 
    D.setElement (1,1, 4);  
    D.setElement (2,0, 5); 
    D.setElement (2,1, 6);  
    System.out.println("D(3,2) using toString():\n" + D);
	 System.out.println(); 
    int[][] y ={
      {1,2},
      {3,4},
      {5,6}
    }; 
	 D = new TwoDArray (3,2,y);
    System.out.println("D(3,2,y): using toString()\n" + D);
	 System.out.println();    
    System.out.println("D.getNumberOfElements():"+D.getNumberOfElements());
    System.out.println("D.getNumberOfRows():"+D.getNumberOfRows());
    System.out.println("D.getNumberOfColumns():"+D.getNumberOfColumns());  
	 System.out.println();    
    System.out.println("D's elements in row-major order using getElement():");
    System.out.println(D.getElement(0,0));
    System.out.println(D.getElement(0,1));  
    System.out.println(D.getElement(1,0));
    System.out.println(D.getElement(1,1)); 
    System.out.println(D.getElement(2,0));
    System.out.println(D.getElement(2,1)); 
	 System.out.println();     
    System.out.println("Offsets of D's elements in row-major order:");
    System.out.println(D.getIndex(0,0));
    System.out.println(D.getIndex(0,1));
    System.out.println(D.getIndex(1,0));
    System.out.println(D.getIndex(1,1));
    System.out.println(D.getIndex(2,0));  
    System.out.println(D.getIndex(2,1));  
	 System.out.println();     
    System.out.println("D.sum():"+ D.sum()); 
	 System.out.println();     
    System.out.println("D's indices:");
    int[] indices = D.getIndices(0);
    System.out.println("D.getIndices(0):" + indices[0]+","+indices[1]);  
    indices = D.getIndices(1);
    System.out.println("D.getIndices(1):"+indices[0]+","+indices[1]);    
    indices = D.getIndices(2);
    System.out.println("D.getIndices(2):"+indices[0]+","+indices[1]);   
    indices = D.getIndices(3);  
    System.out.println("D.getIndices(3):"+indices[0]+","+indices[1]); 
    indices = D.getIndices(4);  
    System.out.println("D.getIndices(4):"+indices[0]+","+indices[1]);
    indices = D.getIndices(5);  
    System.out.println("D.getIndices(5):"+indices[0]+","+indices[1]);  
	 System.out.println();     
    System.out.println("D.transpose():"+D.transpose());
	 System.out.println(); 
	 
		int[][] z ={
      {6,5,4},
      {3,2,1}
    }; 
	 TwoDArray E = new TwoDArray (2,3,z);
    System.out.println("E(2,3,z): using toString()\n" + E);
	 System.out.println();    
    System.out.println("E.getNumberOfElements():"+E.getNumberOfElements());
    System.out.println("E.getNumberOfRows():"+E.getNumberOfRows());
    System.out.println("E.getNumberOfColumns():"+E.getNumberOfColumns());  
	 System.out.println();    
    System.out.println("E's elements in row-major order using getElement():");
    System.out.println(E.getElement(0,0));
    System.out.println(E.getElement(0,1));  
    System.out.println(E.getElement(0,2));
    System.out.println(E.getElement(1,0)); 
    System.out.println(E.getElement(1,1));
    System.out.println(E.getElement(1,2)); 
	 System.out.println();     
    System.out.println("Offsets of E's elements in row-major order:");
    System.out.println(E.getIndex(0,0));
    System.out.println(E.getIndex(0,1));
    System.out.println(E.getIndex(0,2));
    System.out.println(E.getIndex(1,0));
    System.out.println(E.getIndex(1,1));  
    System.out.println(E.getIndex(1,2));  
	 System.out.println();     
    System.out.println("E.sum():"+ E.sum()); 
	 System.out.println();     
    System.out.println("E's indices:");
    indices = E.getIndices(0);
    System.out.println("E.getIndices(0):" + indices[0]+","+indices[1]);  
    indices = E.getIndices(1);
    System.out.println("E.getIndices(1):"+indices[0]+","+indices[1]);    
    indices = E.getIndices(2);
    System.out.println("E.getIndices(2):"+indices[0]+","+indices[1]);   
    indices = E.getIndices(3);  
    System.out.println("E.getIndices(3):"+indices[0]+","+indices[1]); 
    indices = E.getIndices(4);  
    System.out.println("E.getIndices(4):"+indices[0]+","+indices[1]);
    indices = E.getIndices(5);  
    System.out.println("E.getIndices(5):"+indices[0]+","+indices[1]);  
	 System.out.println();     
    System.out.println("E.transpose():"+E.transpose());
	 System.out.println();
  }
} 
