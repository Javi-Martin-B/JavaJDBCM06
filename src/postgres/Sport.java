package postgres;

public class Sport {
    private int id=1;
    private String name;

    public Sport(String name) {
        this.name = name;
    }

    public Sport(int id, String name) {
        this.id = id+1;
        this.name = name;
    }

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

   
}