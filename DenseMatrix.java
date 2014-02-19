public class DenseMatrix {
  int numberOfRows;  //the number of dimensions of this multiarray, i.e., the value of 'n'
  int numberOfColumns;
  TwoDArray data;
  
  public DenseMatrix(int numberOfRows, int numberOfColumns){ //constructs an empty dense matrix of given # rows and cols
	  if(numberOfRows > 0 && numberOfColumns > 0){
		  this.data = new TwoDArray(numberOfRows,numberOfColumns);
		  this.numberOfColumns = numberOfColumns;
		  this.numberOfRows = numberOfRows;
	  }
	  else{
		  throw new IllegalArgumentException();
	  }
  }
  
  public DenseMatrix(int numberOfRows,int numberOfColumns,int y[][] ){ //constructs Dense Matrix with given values from array
	  if(numberOfRows > 0 && numberOfColumns > 0){
		  this.data = new TwoDArray(numberOfRows,numberOfColumns,y);
		  this.numberOfColumns = numberOfColumns;
		  this.numberOfRows = numberOfRows;
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
	  return this.data.getNumberOfElements();
  }    
    
  public void setElement(int row, int column, int value) { //sets element of matrix at (row,column) to value
	  if(row >= 0 && row  <= this.numberOfRows - 1  && column >= 0 && column <= this.numberOfColumns - 1){
		  this.data.setElement(row, column, value);
	  }
	  else{
		  throw new IllegalArgumentException();
	  }
	  
  }

  public int getElement(int row, int column) { //returns element at (row, column)
	  if(row >= 0 && row  <= this.numberOfRows - 1  && column >= 0 && column <= this.numberOfColumns - 1){
		  return this.data.getElement(row, column);
	  }
	  else{
		  throw new IllegalArgumentException();
	  }
  }
  
  public int sum() {
	  return this.data.sum();
  }
     
  public DenseMatrix transpose(){ // returns transpose of matrix
	  DenseMatrix transposed = new DenseMatrix(this.numberOfColumns,this.numberOfRows);
	  for(int i = 0; i < this.numberOfRows; i++){
		  for(int j = 0; j < this.numberOfColumns; j++){
			  transposed.setElement(j, i, this.data.getElement(i, j));
		  }
	  }
	  return transposed;
	  
  }
  
  public static int DOT(DenseMatrix D1, DenseMatrix D2, int D1row, int D2row) {
	  if(D1.numberOfColumns != D2.numberOfColumns || D1row > D1.numberOfRows || D2row > D2.numberOfRows){
		  throw new IllegalArgumentException();
	  }
	  int total = 0;
		  for(int j = 0; j < D1.getNumberOfColumns(); j++){
			  total += D1.getElement(D1row, j) * D2.getElement(D2row, j);
		  }
	  
	  return total;
  }  
  
  public DenseMatrix multiply(DenseMatrix second){ //multiplies this Dense Matrics with a second DenseMatrix and returns the result
	  if(this.numberOfColumns != second.numberOfRows){
		  throw new IllegalArgumentException();
	  }
	  
	  DenseMatrix result = new DenseMatrix(this.numberOfRows,second.getNumberOfColumns());
	  int temp;
	  for(int i = 0; i < getNumberOfRows(); i++){
		  for(int k = 0; k < second.getNumberOfColumns(); k++){
			  temp = 0;
			  for(int j = 0; j < getNumberOfColumns(); j++){
				  temp += this.data.getElement(i, j)*second.getElement(j, k);
			  }
			  result.setElement(i, k, temp);
			  
		  }
	  }
	  return result;
  }
  
  
  public DenseMatrix multiplyTranspose(DenseMatrix B){ //Another method of multiplication which utilizes the transpose
	  if(this.numberOfColumns != B.numberOfRows){
		  throw new IllegalArgumentException();
	  }
	  DenseMatrix BTranspose = B.transpose();
	  DenseMatrix result = new DenseMatrix(getNumberOfRows(),B.getNumberOfColumns());
	  for(int i = 0; i < getNumberOfRows(); i++){
		  for(int k = 0; k < B.getNumberOfColumns(); k++){
			  result.setElement(i, k, DOT(this,BTranspose,i,k));
		  }
	  }
	  return result;
  }
  
  public String toString() {
	  return this.data.toString();
  }
  
  public boolean equals(Object rhs) { //verifies to matrices are equal
	  for(int i = 0; i < this.numberOfRows; i++){
		  for(int k = 0; k < this.numberOfColumns; k++){
			  if(this.data.getElement(i, k)!=((DenseMatrix) rhs).getElement(i,k)){
				  return false;
			  }
		  }
	  }
	  return true;

  	}
  
  public static void main(String args[]) {
  	 DenseMatrix D1 = new DenseMatrix (3,2);  
    D1.setElement (0,0, 1); 
    D1.setElement (0,1, 2); 
    D1.setElement (1,0, 3); 
    D1.setElement (1,1, 4);  
    D1.setElement (2,0, 5); 
    D1.setElement (2,1, 6);   
    System.out.println("D1(3,2) using toString():\n" + D1);
 	 System.out.println();     
    System.out.println("D1.sum(): "+D1.sum());
 	 System.out.println();     
    int[][] y ={
      {1,2},
      {3,4},
      {5,6}
    }; 
    
	 DenseMatrix D2 = new DenseMatrix (3,2,y);
    System.out.println("D2(3,2,y): using toString()\n" + D2);
    System.out.println("D2.getNumberOfElements():"+D2.getNumberOfElements());
    System.out.println("D2.getNumberOfRows():"+D2.getNumberOfRows());
    System.out.println("D2.getNumberOfColumns():"+D2.getNumberOfColumns());  
 	 System.out.println();  
    System.out.println("D2's elements in row-major order using getElement():");
    System.out.println(D2.getElement(0,0));
    System.out.println(D2.getElement(0,1));  
    System.out.println(D2.getElement(1,0));
    System.out.println(D2.getElement(1,1)); 
    System.out.println(D2.getElement(2,0));
    System.out.println(D2.getElement(2,1)); 
	 System.out.println();    
	 
    DenseMatrix D3=new DenseMatrix (2,3);
    D3.setElement (0,0, 1); 
    D3.setElement (0,1, 3); 
    D3.setElement (0,2, 5); 
    D3.setElement (1,0, 2);  
    D3.setElement (1,1, 4); 
    D3.setElement (1,2, 6); 
    
    System.out.println("Multiply");    
    DenseMatrix P=D2.multiply(D3);
    System.out.println("D2:\n"+D2);
    System.out.println("D3:\n"+D3);
    System.out.println("D2.multiply(D3):\n"+ D2.multiply(D3));
  
    System.out.println("D1.equals(D2):"+D1.equals(D2));
    System.out.println("D2.equals(D3):"+D2.equals(D3));    
	 System.out.println();
	 
    int[][] thirdArray={
      {1,2},
      {3,4}
    };
    int[][] fourthArray = { 
      {1,3},
      {2,4}
    };
    
    DenseMatrix A=new DenseMatrix(2,2,thirdArray);
    DenseMatrix B=new DenseMatrix(2,2,fourthArray);
    System.out.println("A:\n"+A);
    System.out.println("B:\n"+B);
    System.out.println("A.multiply(B):\n"+A.multiply(B));
    
    System.out.println("Multiply with transpose:");
    System.out.println("B.transpose():\n" + B.transpose());
    System.out.println("DOT(A,B.transpose(),1,1):"+DenseMatrix.DOT(A,B.transpose(),1,0));
	 System.out.println();    
    System.out.println("A.multiplyTranspose(B):\n"+A.multiplyTranspose(B));
  }
}
