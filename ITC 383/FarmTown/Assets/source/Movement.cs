using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movement : MonoBehaviour
{
    float speed = 0.04f;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKey(KeyCode.Space))
        {
            Vector2 pos = transform.position;
            pos.y = pos.y + (speed * 1.5f);
            transform.position = pos;
        }

        if (Input.GetKey(KeyCode.A))
        {
            Vector2 pos = transform.position;
            pos.x = pos.x - speed;
            transform.position = pos;
        }

        if (Input.GetKey(KeyCode.D))
        {
            Vector2 pos = transform.position;
            pos.x = pos.x + speed;
            transform.position = pos;
        }
    }
}
