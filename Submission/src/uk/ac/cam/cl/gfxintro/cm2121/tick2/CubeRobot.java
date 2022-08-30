package uk.ac.cam.cl.gfxintro.cm2121.tick2;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.CallbackI;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

;

public class CubeRobot {
	
    // Filenames for vertex and fragment shader source code
    private final static String VSHADER_FN = "resources/cube_vertex_shader.glsl";
    private final static String FSHADER_FN = "resources/cube_fragment_shader.glsl";
    
    // Reference to skybox of the scene
    public SkyBox skybox;
    
    // Components of this CubeRobot
    
    // Component 1 : Body
	private Mesh body_mesh;				// Mesh of the body
	private ShaderProgram body_shader;	// Shader to colour the body mesh
	private Texture body_texture;		// Texture image to be used by the body shader
	private Matrix4f body_transform;	// Transformation matrix of the body object
	
	// TODO: Add Component 2: Right Arm

	private Mesh arm_mesh;				// Mesh of the body
	private ShaderProgram arm_shader;	// Shader to colour the body mesh
	private Texture arm_texture;		// Texture image to be used by the body shader
	private Matrix4f arm_transform;	// Transformation matrix of the body object
	
	// Complete rest of the robot

	private Mesh head_mesh;				// Mesh of the body
	private ShaderProgram head_shader;	// Shader to colour the body mesh
	private Texture head_texture;		// Texture image to be used by the body shader

	private Mesh leg_mesh;				// Mesh of the body
	private ShaderProgram leg_shader;	// Shader to colour the body mesh
	private Texture leg_texture;		// Texture image to be used by the body shader
	
/**
 *  Constructor
 *  Initialize all the CubeRobot components
 */
	public CubeRobot() {
		// Create body node
		
		// Initialise Geometry
		body_mesh = new CubeMesh(); 
		
		// Initialise Shader
		body_shader = new ShaderProgram(new Shader(GL_VERTEX_SHADER, VSHADER_FN), new Shader(GL_FRAGMENT_SHADER, FSHADER_FN), "colour");
		// Tell vertex shader where it can find vertex positions. 3 is the dimensionality of vertex position
		// The prefix "oc_" means object coordinates
		body_shader.bindDataToShader("oc_position", body_mesh.vertex_handle, 3);
		// Tell vertex shader where it can find vertex normals. 3 is the dimensionality of vertex normals
		body_shader.bindDataToShader("oc_normal", body_mesh.normal_handle, 3);
		// Tell vertex shader where it can find texture coordinates. 2 is the dimensionality of texture coordinates
		body_shader.bindDataToShader("texcoord", body_mesh.tex_handle, 2);
		
		// Initialise Texturing
		body_texture = new Texture(); 
		body_texture.load("resources/cubemap.png");
		
		// Build Transformation Matrix



		// TODO: Create right arm node


		// Initialise Geometry
		arm_mesh = new CubeMesh();

		// Initialise Shader
		arm_shader = new ShaderProgram(new Shader(GL_VERTEX_SHADER, VSHADER_FN), new Shader(GL_FRAGMENT_SHADER, FSHADER_FN), "colour");
		// Tell vertex shader where it can find vertex positions. 3 is the dimensionality of vertex position
		// The prefix "oc_" means object coordinates
		arm_shader.bindDataToShader("oc_position", arm_mesh.vertex_handle, 3);
		// Tell vertex shader where it can find vertex normals. 3 is the dimensionality of vertex normals
		arm_shader.bindDataToShader("oc_normal", arm_mesh.normal_handle, 3);
		// Tell vertex shader where it can find texture coordinates. 2 is the dimensionality of texture coordinates
		arm_shader.bindDataToShader("texcoord", arm_mesh.tex_handle, 2);

		// Initialise Texturing
		arm_texture = new Texture();
		arm_texture.load("resources/cubemap.png");

		arm_transform = new Matrix4f();


		head_mesh = new CubeMesh();
		head_shader = new ShaderProgram(new Shader(GL_VERTEX_SHADER, VSHADER_FN), new Shader(GL_FRAGMENT_SHADER, FSHADER_FN), "colour");
		head_shader.bindDataToShader("oc_position", head_mesh.vertex_handle, 3);
		head_shader.bindDataToShader("oc_normal", head_mesh.normal_handle, 3);
		head_shader.bindDataToShader("texcoord", head_mesh.tex_handle, 2);
		head_texture = new Texture();
		head_texture.load("resources/cubemap_head.png");

		leg_mesh = new CubeMesh();
		leg_shader = new ShaderProgram(new Shader(GL_VERTEX_SHADER, VSHADER_FN), new Shader(GL_FRAGMENT_SHADER, FSHADER_FN), "colour");
		leg_shader.bindDataToShader("oc_position", leg_mesh.vertex_handle, 3);
		leg_shader.bindDataToShader("oc_normal", leg_mesh.normal_handle, 3);
		leg_shader.bindDataToShader("texcoord", leg_mesh.tex_handle, 2);
		leg_texture = new Texture();
		leg_texture.load("resources/cubemap.png");

		// TODO: Initialise Texturing
		//Skip
		
		// TODO: Build Right Arm's Transformation Matrix (rotate the right arm around its end)
		/*
		Vector3f arm_scale = new Vector3f(0.25f, 1.25f, 0.25f);
		Vector3f arm_rot_translate = new Vector3f(0f, 1f, 0f);
		Vector3f arm_rot_translate_revert = new Vector3f(0f, -1f, 0f);
		Vector3f arm_rot = new Vector3f(0f, 0f, 0.3f); //not used
		Vector3f arm_translate = new Vector3f(1f, -0.25f, 0.125f);

		arm_transform.translate(arm_translate);

		arm_transform.translateLocal(arm_rot_translate);
		arm_transform.rotateAffineXYZ(0f, 0f, 0.3f);
		arm_transform.translateLocal(arm_rot_translate.negate());
		arm_transform.scale(0.25f, 1.25f, 0.25f);
*/
		
		// TODO: Complete robot

	}
	

	/**
	 * Updates the scene and then renders the CubeRobot
	 * @param camera - Camera to be used for rendering
	 * @param deltaTime		- Time taken to render this frame in seconds (= 0 when the application is paused)
	 * @param elapsedTime	- Time elapsed since the beginning of this program in millisecs
	 */
	public void render(Camera camera, float deltaTime, long elapsedTime) {

		float time = elapsedTime * 0.002f;
		float offset = (float) Math.sin(time);
		float spin = 0.1f * 5 * elapsedTime/1000;

		// TODO: Animate Body. Translate the body as a function of time
		//Vector3f body_rot_time = new Vector3f(0f, 0.1f, 0f);
		body_transform = new Matrix4f();
		Vector3f body_scale = new Vector3f(0.75f, 1.5f, 0.75f);
		body_transform.scale(body_scale);
		Matrix4f body_transform_time = new Matrix4f();
		body_transform.rotateAffineXYZ(0f, spin, 0f, body_transform_time);

/*
		// TODO: Animate Arm. Rotate the left arm around its end as a function of time
		Matrix4f arm_transform_time = new Matrix4f();
		Matrix4f arm_transform_mat = new Matrix4f();//arm_transform;

		float offset = (float) Math.sin(elapsedTime*0.002);

		Matrix4f arm_rot_mat = new Matrix4f();

		arm_rot_mat.translate(new Vector3f (0,1,0));
		arm_rot_mat.rotateAffineXYZ(0,0,-deltaTime * Math.signum(offset));
		arm_rot_mat.translate(new Vector3f (0,-1,0));

		arm_transform_mat.translateLocal(new Vector3f(1f, 0.25f, -0.125f));
		//arm_transform_mat.mul(arm_rot_mat);
		arm_transform_mat = arm_rot_mat.mul(arm_transform_mat);
		arm_transform_mat.translateLocal(new Vector3f(-1f, -0.25f, 0.125f));


//This doesnt quite work?
		arm_transform_time = arm_transform.mul(arm_transform_mat);
*/
		//Kind of a weird method since I had bugs in previous implementation
		//that neither me nor demonstrator could fix.

		//Arm flap


		Vector3f arm_scale = new Vector3f(0.25f, 1.25f,0.25f);
		Vector3f left_arm_rot_translate = new Vector3f(0f, 1f, 0f);
		Vector3f left_arm_translate = new Vector3f(1f, -0.25f, 0.125f);
		Vector3f right_arm_rot_translate = new Vector3f(0f, 1f, 0f);
		Vector3f right_arm_translate = new Vector3f(-1f, -0.25f, 0.125f);

		Matrix4f left_arm_transform = new Matrix4f();
		left_arm_transform.translate(left_arm_translate);
		left_arm_transform.translate(left_arm_rot_translate);
		left_arm_transform.rotateAffineXYZ(0f, 0f, time * Math.signum(offset));
		left_arm_transform.translate(left_arm_rot_translate.negate());
		left_arm_transform.scale(arm_scale);
		left_arm_transform.rotateLocal(spin, 0f, 1f, 0f);

		Matrix4f right_arm_transform = new Matrix4f();
		right_arm_transform.translate(right_arm_translate);
		right_arm_transform.translate(right_arm_rot_translate);
		right_arm_transform.rotateAffineXYZ(0f, 0f, -time * Math.signum(offset));
		right_arm_transform.translate(right_arm_rot_translate.negate());
		right_arm_transform.scale(arm_scale);
		right_arm_transform.rotateLocal(spin, 0f, 1f, 0f);

		Matrix4f left_leg_transform = new Matrix4f();
		left_leg_transform.translate(new Vector3f(-0.35f, -2, 0.2f));
		//left_leg_transform.translate(left_arm_rot_translate);
		//left_leg_transform.rotateAffineXYZ(0f, 0f, time * Math.signum(offset));
		//left_leg_transform.translate(left_arm_rot_translate.negate());
		left_leg_transform.scale(new Vector3f(0.25f, 1.25f, 0.25f));
		left_leg_transform.rotateLocal(spin, 0f, 1f, 0f);

		Matrix4f right_leg_transform = new Matrix4f();
		right_leg_transform.translate(new Vector3f(0.35f, -2, 0.2f));
		//left_leg_transform.translate(left_arm_rot_translate);
		//left_leg_transform.rotateAffineXYZ(0f, 0f, time * Math.signum(offset));
		//left_leg_transform.translate(left_arm_rot_translate.negate());
		right_leg_transform.scale(new Vector3f(0.25f, 1.25f, 0.25f));
		right_leg_transform.rotateLocal(spin, 0f, 1f, 0f);

		Matrix4f head_transform = new Matrix4f();
		head_transform.translate(new Vector3f(0f, 2, 0f));
		//left_leg_transform.translate(left_arm_rot_translate);
		//left_leg_transform.rotateAffineXYZ(0f, 0f, time * Math.signum(offset));
		//left_leg_transform.translate(left_arm_rot_translate.negate());
		head_transform.scale(new Vector3f(1f, 1f, 1f));
		head_transform.rotateLocal(spin, 0f, 1f, 0f);


		/*
		//arm_transform.translateLocal(0f,-1f,0f);
		arm_transform.rotateLocal(0.9f,0f,0f,1f);
		arm_transform.rotateLocal(0.3f*(float)Math.sin(elapsedTime/500.0f),0f,0f,1f);
		arm_transform.translateLocal(0.0f,1f,0f);
		arm_transform.translateLocal(1f, -0.25f, 0.125f);
		*/
		//arm_transform.rotateLocal(2*3.14159f*elapsedTime/3000.0f, 0f, 1f, 0f);

		renderMesh(camera, body_mesh, body_transform_time, body_shader, body_texture);
		
		// TODO: Chain transformation matrices of the arm and body (Scene Graph)
/*
		Matrix4f body_without_scale = new Matrix4f(body_transform);
		body_without_scale.scaleLocal(1/0.75f, 1/1.5f, 1/0.75f);
 */
		//Matrix4f arm_transform_time = new Matrix4f();


		// TODO: Render Arm.
		renderMesh(camera, arm_mesh, left_arm_transform, arm_shader, arm_texture);
		renderMesh(camera, arm_mesh, right_arm_transform, arm_shader, arm_texture);
		
		//TODO: Render rest of the robot

		renderMesh(camera, leg_mesh, left_leg_transform, leg_shader, leg_texture);
		renderMesh(camera, leg_mesh, right_leg_transform, leg_shader, leg_texture);
		renderMesh(camera, head_mesh, head_transform, head_shader, head_texture);
		

		
	}
	
	/**
	 * Draw mesh from a camera perspective
	 * @param camera		- Camera to be used for rendering
	 * @param mesh			- mesh to render
	 * @param modelMatrix	- model transformation matrix of this mesh
	 * @param shader		- shader to colour this mesh
	 * @param texture		- texture image to be used by the shader
	 */
	public void renderMesh(Camera camera, Mesh mesh , Matrix4f modelMatrix, ShaderProgram shader, Texture texture) {
		// If shaders modified on disk, reload them
		shader.reloadIfNeeded(); 
		shader.useProgram();

		// Step 2: Pass relevant data to the vertex shader
		
		// compute and upload MVP
		Matrix4f mvp_matrix = new Matrix4f(camera.getProjectionMatrix()).mul(camera.getViewMatrix()).mul(modelMatrix);
		shader.uploadMatrix4f(mvp_matrix, "mvp_matrix");
		
		// Upload Model Matrix and Camera Location to the shader for Phong Illumination
		shader.uploadMatrix4f(modelMatrix, "m_matrix");
		shader.uploadVector3f(camera.getCameraPosition(), "wc_camera_position");
		
		// Transformation by a nonorthogonal matrix does not preserve angles
		// Thus we need a separate transformation matrix for normals

		//TODO: Calculate normal transformation matrix
		Matrix3f normal_matrix = new Matrix3f();
		modelMatrix.get3x3(normal_matrix);
		normal_matrix = new Matrix3f((normal_matrix.invert().transpose()));
		shader.uploadMatrix3f(normal_matrix, "normal_matrix");
		
		// Step 3: Draw our VertexArray as triangles
		// Bind Textures
		texture.bindTexture();
		shader.bindTextureToShader("tex", 0);
		skybox.bindCubemap();
		shader.bindTextureToShader("skybox", 1);
		// draw
		glBindVertexArray(mesh.vertexArrayObj); // Bind the existing VertexArray object
		glDrawElements(GL_TRIANGLES, mesh.no_of_triangles, GL_UNSIGNED_INT, 0); // Draw it as triangles
		glBindVertexArray(0);             // Remove the binding
		
        // Unbind texture
		texture.unBindTexture();
		skybox.unBindCubemap();
	}
}
