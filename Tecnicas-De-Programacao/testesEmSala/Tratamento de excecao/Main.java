class Main{
	public static void main(String args[]){
		Pessoa p = null;
		try{
			p = new Pessoa(0);
		}catch(AgeException e){
			e.printStackTrace();
			//System.exit(0);
		}
		
		System.out.println(p);
	}
}