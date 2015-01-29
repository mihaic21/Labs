using UnityEngine;
using System.Collections;

public class GameScript: MonoBehaviour {
	public enum GameStates {
		stopped,
		sleeping,
		walking,
		wallHit,
		dying,
		dead
	};

	public static GameStates gameState = GameStates.stopped;
	public GameObject spider;
	public GameObject boot;

	public GameObject mainCamera;

	public Vector3 spiderStartPosition = new Vector3(0,0,3);
	public Vector3 spiderEndPosition = new Vector3(0,0.35f,-2.5f);

	public Vector3 startCameraPosition = new Vector3(262.83f,-85f,112.2f);
	public Vector3 endCameraPosition = new Vector3 (266, -73.2f, 99.7f);

	public Vector3 bootStartPosition = new Vector3 (9, 18, 0);
	public Vector3 bootEndPosition = new Vector3 (3.23f, 0.68f, -1);
	public float bootRotation = 30;

	public static Animation spiderAnimation;
	public static int currentLevel = 0;

	private static GameScript _instance;
	public static GameScript Instance
	{
		get
		{
			if(_instance == null)
				_instance = (GameScript) (Resources.FindObjectsOfTypeAll(typeof(GameScript))[0]);
			//Debug.Log (_instance);
			return _instance;
		}
	}

	// Use this for initialization
	void Start () {
		spiderAnimation = spider.GetComponent<Animation> ();


	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public static void spiderWakeUp() {
		GameScript.gameState = GameScript.GameStates.walking;
		SpecialEffectsScript.StopAllAudio ();
		SpecialEffectsScript.PlayAlertSound ();
		spiderAnimation.clip = spiderAnimation.GetClip ("taunt");
		spiderAnimation.Play ();
	}

	private IEnumerator startWalking(float delay = 0f) {
		yield return new WaitForSeconds (delay);
		spider.transform.localRotation = new Quaternion (0, 180, 0, 1);
		spider.transform.position = spiderStartPosition;
		spiderAnimation.clip = spiderAnimation.GetClip ("walk");
		spiderAnimation.Play ();
		Instance.StartCoroutine (Instance.slideSpider (10));
	}

	private IEnumerator startRunning(float delay = 0f) {
		spider.transform.position = spiderStartPosition;
		spider.transform.localRotation = new Quaternion (0, 180, 0, 1);
		yield return new WaitForSeconds (delay);
		spiderAnimation.clip = spiderAnimation.GetClip ("run");
		spiderAnimation.Play ();
		Instance.StartCoroutine (Instance.slideSpider (5));
	}

	private IEnumerator stopSpider() {
		spiderAnimation.clip = spiderAnimation.GetClip ("taunt");
		spiderAnimation.Stop ();
		yield return null;
	}

	float bigDeltaSlide = 0;
	float lastSlideDuration;
	private IEnumerator slideSpider(float duration = 10.0f) {
		spider.transform.rotation = new Quaternion (0, 180, 0, 1);
		bigDeltaSlide = 0;
		lastSlideDuration = duration;
		while (bigDeltaSlide<duration) {
			bigDeltaSlide += Time.deltaTime;
			float ratio = bigDeltaSlide/duration;
			spider.transform.position = Vector3.Lerp (spiderStartPosition,spiderEndPosition,ratio);
			spider.transform.Rotate(Vector3.left*ratio*0.1f);
			yield return null;
		}
		spider.transform.position = spiderEndPosition;
	}

	private IEnumerator slideSpiderBackwards() {
		while (bigDeltaSlide>0) {
			bigDeltaSlide -= 2*Time.deltaTime;
			float ratio = bigDeltaSlide/lastSlideDuration;
			spider.transform.position = Vector3.Lerp (spiderStartPosition,spiderEndPosition,ratio);
			spider.transform.Rotate(Vector3.right*ratio*0.1f);
			yield return null;
		}
		spider.transform.position = spiderStartPosition;
		spider.transform.rotation = new Quaternion (0, 180, 0, 1);
	}

	public static void spiderStartSleeping() {
		GameScript.gameState = GameScript.GameStates.sleeping;
		spiderAnimation.Stop ();
		spiderAnimation.clip = spiderAnimation.GetClip ("idle");
		spiderAnimation.Play ();
	}

	public static void positionCameraAtStart(float delay=0.4f) {
		Instance.StartCoroutine(Instance.slideCamera(Instance.startCameraPosition,delay));
	}


	IEnumerator scrollPlaneCo(float speed=1.0f, float delay = 0.0f) {
		yield return new WaitForSeconds (delay);
		float lastPos = GUI.Instance.plane.renderer.material.GetTextureOffset ("_MainTex").y;
		float bigDeltaScroll = 0;
		while (true) {
			bigDeltaScroll += Time.deltaTime;
			GUI.Instance.plane.renderer.material.SetTextureOffset("_MainTex", new Vector3(0,lastPos+speed*bigDeltaScroll,0));
			yield return null;
		}
	}

	public void startLevel(int level) {
		if (level<6)
			GUI.Instance.gameText.text = "Scared?\nTap to block the spider";
		Debug.Log (level);
		currentLevel = level;
		Instance.StopAllCoroutines ();
		SpecialEffectsScript.StopAllAudio ();
		if (level>1 && level<6)
			SpecialEffectsScript.PlayWallFallingSound();
		switch(level) {
			case 0:
				boot.SetActive(false);
				spider.transform.localScale = Vector3.one;
				spider.transform.position = spiderStartPosition;
			break;
			case 1:
				gameState = GameStates.walking;
				boot.SetActive(false);
				spider.transform.localScale = Vector3.one;
				Instance.StartCoroutine(startWalking(1.0f));
				Instance.StartCoroutine (Instance.scrollPlaneCo(0.07f, 1.0f));
				SpecialEffectsScript.PlayWalkinSound();
				SpecialEffectsScript.PlayAlertSound();				
				StartCoroutine(slideCamera(startCameraPosition,0.5f));
			break;

			case 2:
				gameState = GameStates.walking;
				spider.transform.localScale = Vector3.one*1.1f;
				Instance.StartCoroutine(startWalking());
				Instance.StartCoroutine (Instance.scrollPlaneCo(0.07f));
				SpecialEffectsScript.PlayWalkinSound();
			break;

			case 3:
				gameState = GameStates.walking;
				spider.transform.localScale = Vector3.one*1.2f;
				Instance.StartCoroutine(startWalking());
				Instance.StartCoroutine (Instance.scrollPlaneCo(0.07f));
				SpecialEffectsScript.PlayWalkinSound();
			break;

			case 4:
				gameState = GameStates.walking;
				spider.transform.localScale = Vector3.one*1.3f;
				Instance.StartCoroutine(startRunning());
				Instance.StartCoroutine (Instance.scrollPlaneCo(0.14f));
				SpecialEffectsScript.PlayWalkinSound();
				SpecialEffectsScript.PlayRunningSound();
			break;

			case 5:
				gameState = GameStates.walking;
				spider.transform.localScale = Vector3.one*1.4f;
				Instance.StartCoroutine (Instance.scrollPlaneCo(0.14f));
				Instance.StartCoroutine(startRunning());				
				SpecialEffectsScript.PlayWalkinSound();
				SpecialEffectsScript.PlayRunningSound();
			break;

			case 6:
				killSpider();
			break;
		}

	}

	IEnumerator slideCamera(Vector3 to, float duration = 1.0f) {
		Vector3 from = Instance.mainCamera.transform.localPosition;
		Debug.Log (from);
		float bigDelta = 0;
		while (bigDelta<duration) {
			bigDelta+=Time.deltaTime;
			mainCamera.transform.localPosition = Vector3.Lerp(from, to, bigDelta/duration);
			yield return null;
		}
	}

	public static void createWall() {
		if (currentLevel<4)
			WallScript.createMovingWall (16.5f);
		else if (currentLevel<6)
			WallScript.createMovingWall (8.25f);

		//advanceLevel ();
	}

	public static void advanceLevel(float delay = 0) {
		if (delay>0) {
			Instance.StopAllCoroutines();
			if (currentLevel<4)
				Instance.StartCoroutine (Instance.scrollPlaneCo(0.07f));
			else
				Instance.StartCoroutine (Instance.scrollPlaneCo(0.14f));
			Instance.StartCoroutine(Instance.slideSpiderBackwards());
			Instance.StartCoroutine(Instance.advanceLevelCo(Instance.bigDeltaSlide/2));
		}
		else {
			Instance.startLevel (currentLevel+1);
		}
	}

	IEnumerator advanceLevelCo(float delay) {
		yield return new WaitForSeconds (delay);
		Instance.startLevel(currentLevel+1);
	}

	public static void killSpider() {
		//Instance.StartCoroutine(Instance.startRunning());
		//Instance.StartCoroutine (Instance.scrollPlaneCo(0.14f));
		Instance.StopAllCoroutines ();
		Instance.bootEndPosition.z = Instance.spider.transform.position.z;
		SpecialEffectsScript.StopAllAudio ();
		Instance.StartCoroutine(Instance.slideCamera(Instance.endCameraPosition,0.6f));
		Instance.boot.SetActive (true);
		Instance.boot.transform.position = Instance.bootStartPosition;
		Instance.boot.transform.eulerAngles = new Vector3 (0, 270, 0);
		Instance.StartCoroutine (Instance.crushSpider ());
		gameState = GameStates.dying;
		SpecialEffectsScript.PlayDieSound (0.6f);
	}

	IEnumerator crushSpider(float duration = 0.5f) {
		WallScript.destroyWall ();
		float bigDelta = 0;
		yield return new WaitForSeconds (0.4f);
		while (bigDelta<duration) {
			bigDelta+= Time.deltaTime;
			boot.transform.position = Vector3.Lerp(bootStartPosition,bootEndPosition,bigDelta/duration);
			boot.transform.eulerAngles = Vector3.Lerp (new Vector3(-bootRotation,270,0), new Vector3(0,270,0), bigDelta/duration);
			yield return null;
		}
		gameState = GameStates.dead;
	}


	
}
