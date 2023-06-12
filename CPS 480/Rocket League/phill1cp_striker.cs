using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class phill1cp_striker : CarController {

	float vPos = 0, hPos = 0;

	// Use this for initialization
	void Start () {
		playerName = "phill1cp-striker";
		Init();
		StartCoroutine(UpdateControls());
	}

	IEnumerator UpdateControls(){
		while (1==1){
			yield return new WaitForSeconds(0.1f);
			
			if(transform.position.x < ball.transform.position.x) {
				hPos = 1;
			} else {
				hPos = -1;
			}

			if(transform.position.z < ball.transform.position.z) {
				vPos = 1;
			} else {
				vPos = -1;
			}		

			controller.verticalSetting = vPos;
			controller.horizontalSetting = hPos;
		}
	}
}
