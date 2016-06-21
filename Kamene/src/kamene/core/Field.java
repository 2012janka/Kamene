package kamene.core;

import java.io.Serializable;

public class Field implements Serializable {
	private final Tile[][] tiles;

	private final int rowCount;

	private final int columnCount;

	private GameState state = GameState.PLAYING;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];

		generate();
	}

	public GameState getState() {
		return state;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	public int getNumberOfTiles() {
		return columnCount * rowCount - 1;
	}

	public void setGameState(GameState state) {
		if (isSolved()) {
			this.state = GameState.SOLVED;
		} else {
			this.state = state;
		}
	}

	private void generate() {
		int i = 1;

		for (int m = 0; m < this.getRowCount(); m++) {
			for (int n = 0; n < this.getColumnCount(); n++) {
				tiles[m][n] = new Tile(i);
				i++;

			}
		}

		for (int m = 0; m < this.getRowCount(); m++) {
			for (int n = 0; n < this.getColumnCount(); n++) {
				shuffleColumns(n);
				shuffleRows(m);
			}
		}
	}

	private void shuffleColumns(int column) {
		int dlzka = getRowCount();
		for (int i = 0; i < dlzka; i++) {
			int s = i + (int) (Math.random() * (dlzka - i));

			Tile temp = tiles[s][column];
			tiles[s][column] = tiles[i][column];
			tiles[i][column] = temp;
		}
	}

	private void shuffleRows(int row) {
		int dlzka = getColumnCount();
		for (int i = 0; i < dlzka; i++) {
			int s = (int) (Math.random() * (dlzka - i)) + i;

			Tile temp = tiles[row][s];
			tiles[row][s] = tiles[row][i];
			tiles[row][i] = temp;
		}
	}

	public int[] getEmptyTile() {
		int[] indexes = new int[2];
		for (int m = 0; m < this.getRowCount(); m++) {
			for (int n = 0; n < this.getColumnCount(); n++) {
				if (tiles[m][n].getValue() == getNumberOfTiles() + 1) {
					indexes[0] = m;
					indexes[1] = n;
				}
			}
		}
		return indexes;
	}

	public boolean[] borders() {
		boolean border[] = new boolean[4]; // 0 up, 1 down, 2 left, 3 right
		if (getEmptyTile()[0] != rowCount - 1) {
			border[0] = true;
		}
		if (getEmptyTile()[0] != 0) {
			border[1] = true;
		}
		if (getEmptyTile()[1] != columnCount - 1) {
			border[2] = true;
		}
		if (getEmptyTile()[1] != 0) {
			border[3] = true;
		}
		return border;
	}

	private boolean isSolved() {
		int i = 1;
		for (int m = 0; m < this.getRowCount(); m++) {
			for (int n = 0; n < this.getColumnCount(); n++) {
				if (tiles[m][n].getValue() != i) {
					return false;
				}
				i++;
			}
		}
		return true;
	}
}
