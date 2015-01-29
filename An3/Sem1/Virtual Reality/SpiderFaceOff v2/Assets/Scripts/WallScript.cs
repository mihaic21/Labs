using UnityEngine;
using System.Collections;

public class WallScript : MonoBehaviour {

	public GameObject wallPrefab;

	public Vector3 startPosition;
	public Vector3 endPosition;

	public GameObject wall;


	private static WallScript _instance;
	public static WallScript Instance
	{
		get
		{
			if(_instance == null)
				_instance = (WallScript) (Resources.FindObjectsOfTypeAll(typeof(WallScript))[0]);
			//Debug.Log (_instance);
			return _instance;
		}
	}
	// Use this for initialization
	void Start () {
	
	}

	public static void destroyWall() {
		if (Instance.wall!=null)
			Destroy (Instance.wall);
	}

	public static void createMovingWall(float duration) {
		if (Instance.wall!=null)
			Destroy (Instance.wall);
		Instance.StopAllCoroutines ();
		Instance.wall = (GameObject) Instantiate (Instance.wallPrefab, Instance.startPosition, new Quaternion (0, 0, 0, 0));
		Instance.StartCoroutine (Instance.moveWall (duration));
	}

	IEnumerator moveWall(float duration) {
		float bigDelta = 0;
		while (bigDelta<duration) {
			bigDelta += Time.deltaTime;
			if (wall==null)
				break;
			wall.transform.position = Vector3.Lerp(startPosition, endPosition, bigDelta/duration);
			yield return null;
		}
		Destroy(wall);
		yield return null;
	}

	public static void disableWall() {
		if (Instance.wall!=null) {
			GameObject[] x = GameObject.FindGameObjectsWithTag("Zid");
			foreach(GameObject g in x) {
				g.tag = "Untagged";
			}
		}
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
